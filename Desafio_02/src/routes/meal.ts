import { FastifyInstance } from 'fastify'
import { knex } from '../database'
import { z } from 'zod'
import crypto, { randomUUID } from 'node:crypto'
import { checkSessionIdExists } from '../middlewares/check-session-id-exists'

export async function mealsRoutes(app: FastifyInstance) {

  app.post('/', async (request, reply) => {

    const createMealBodySchema = z.object({
      name: z.string(),
      description: z.string(),
      diet: z.boolean(),
    })

    const { name, description, diet } = createMealBodySchema.parse(request.body)

    let sessionId = request.cookies.sessionId

    if (!sessionId) {
      sessionId = randomUUID()

      reply.cookie('sessionId', sessionId, {
        path: '/',
        maxAge: 1000 * 60 * 60 * 24 * 7 // 7 days
      })

    }

    await knex('meal')
      .insert({
        id: crypto.randomUUID(),
        name,
        description,
        diet,
        session_id: sessionId
      })

    return reply.status(201).send()
  })

  app.get('/', { preHandler: [checkSessionIdExists] }, async (request, reply) => {

    const { sessionId } = request.cookies

    const meals = await knex('meal')
      .where('session_id', sessionId)
      .select()

    return { meals }
  })

  app.get('/:id', { preHandler: [checkSessionIdExists] }, async (request, reply) => {
    const getMealParamsSchema = z.object({
      id: z.string().uuid()
    })

    const { sessionId } = request.cookies

    const { id } = getMealParamsSchema.parse(request.params)

    const meal = await knex('meal')
      .where({
        id: id,
        session_id: sessionId
      })
      .first()

    return { meal }
  })

  app.delete('/:id', { preHandler: [checkSessionIdExists] }, async (request, reply) => {
    const getMealParamsSchema = z.object({
      id: z.string().uuid()
    })

    const { sessionId } = request.cookies

    const { id } = getMealParamsSchema.parse(request.params)

    await knex('meal')
      .where({
        id: id,
        session_id: sessionId
      })
      .first()
      .delete()

    return `Refeição ID:${id} deletada com sucesso!`
  })

  app.put('/:id', { preHandler: [checkSessionIdExists] }, async (request, reply) => {
    const getMealParamsSchema = z.object({
      id: z.string().uuid(),
    })

    const createMealBodySchema = z.object({
      name: z.string(),
      description: z.string(),
      datetime: z.string(),
      diet: z.boolean()
    })

    const { sessionId } = request.cookies

    const { id } = getMealParamsSchema.parse(request.params)

    const { name, description, datetime, diet } = createMealBodySchema.parse(request.body)

    const meal = await knex('meal')
      .where({
        id: id,
        session_id: sessionId
      })
      .update({
        name: name,
        description: description,
        datetime: datetime,
        diet: diet
      })

    return reply.status(204).send()
  })

  app.get('/metrics', { preHandler: [checkSessionIdExists] }, async (request, reply) => {
    const { sessionId } = request.cookies

    const metricsTotalMeals = await knex('meal')
      .where('session_id', sessionId)
      .count('id', { as: 'Quantidade total de refeições registradas' })

    const metricsTotalDietMeals = await knex('meal')
      .where('session_id', sessionId)
      .andWhere('diet', 1)
      .count('diet', { as: 'Quantidade total de refeições dentro da dieta' })

    const metricsTotalOutDietMeals = await knex('meal')
      .where('session_id', sessionId)
      .andWhere('diet', 0)
      .count('diet', { as: 'Quantidade total de refeições fora da dieta' })

    const metricsBestDietMeals = await knex('meal')
      .where('session_id', sessionId)
      .andWhere('diet', 1)
      .orderBy('datetime');

    let maxDietMeals = 0;
    let currentDietMeals = 0;
    let bestSequenceDietMeals: any[] = [];
    let currentSequence: any[] = [];

    for (const meal of metricsBestDietMeals) {
      if (meal.diet) {
        currentDietMeals++;
        currentSequence.push(meal);
      } else {
        if (currentDietMeals > maxDietMeals) {
          maxDietMeals = currentDietMeals;
          bestSequenceDietMeals = [...currentSequence];
        }
        currentDietMeals = 0;
        currentSequence = [];
      }
    }

    if (currentDietMeals > maxDietMeals) {
      maxDietMeals = currentDietMeals;
      bestSequenceDietMeals = [...currentSequence];
    }

    return {
      metricsTotalMeals,
      metricsTotalDietMeals,
      metricsTotalOutDietMeals,
      metricsBestDietMeals: {
        bestSequenceDietMeals: bestSequenceDietMeals,
      },
    };
  });


}