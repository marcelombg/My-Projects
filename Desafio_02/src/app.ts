import fastify from 'fastify'
import { usersRoutes } from './routes/user'
import { mealsRoutes } from './routes/meal'
import cookie from '@fastify/cookie'

export const app = fastify()

app.addHook('preHandler', async (request, reply) => {
  console.log(`[${request.method}] ${request.url}`)
})
app.register(cookie)
app.register(usersRoutes, {
  prefix: 'user'
})
app.register(mealsRoutes, {
  prefix: 'meal'
})