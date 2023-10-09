import { FastifyInstance } from 'fastify'
import { knex } from '../database'
import { z } from 'zod'
import crypto, { randomUUID } from 'node:crypto'
import { checkSessionIdExists } from '../middlewares/check-session-id-exists'

export async function usersRoutes(app: FastifyInstance) {

    app.get('/', { preHandler: [checkSessionIdExists] }, async (request, reply) => {

    const { sessionId } = request.cookies

    const users = await knex('user')
    .where('session_id', sessionId)
    .select()

    return { users }
  })

  app.get('/:id', { preHandler: [checkSessionIdExists] }, async (request, reply) => {
    const getUserParamsSchema = z.object({
      id: z.string().uuid()
    })

    const { sessionId } = request.cookies

    const { id } = getUserParamsSchema.parse(request.params)

    const user = await knex('user')
    .where({
      id:id,
      session_id: sessionId
    })
    .first()

    return { user }
  })

  app.get('/summary', { preHandler: [checkSessionIdExists] }, async (request, reply) => {
    const { sessionId } = request.cookies

    const summary = await knex('transactions').where('session_id', sessionId).sum('amount', { as: 'amount' }).first()

    return { summary }
  })
  
  app.post('/', async (request, reply) => {

    const createUsersBodySchema = z.object({
        name: z.string(),
        email: z.string()
    })

    const { name, email } = createUsersBodySchema.parse(request.body)

    let sessionId = request.cookies.sessionId

    if (!sessionId) {
      sessionId = randomUUID()

      reply.cookie('sessionId', sessionId, {
        path: '/',
        maxAge: 1000 * 60 * 60 * 24 * 7 // 7 days
      })

    }

    await knex('user')
      .insert({
        id: crypto.randomUUID(),
        name,
        email,
        session_id: sessionId
      })

    return reply.status(201).send()
  })
}