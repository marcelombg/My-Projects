import request from 'supertest'
import { app } from '@/app'
import { describe, it, afterAll, beforeAll, expect } from 'vitest'
import { createAndAuthenticateUser } from '@/utils/test/create-and-authenticate-user'

describe('Create Gym (e2e)', () => {

    beforeAll(async () => {
        await app.ready()
    })

    afterAll(async () => {
        await app.close()
    })

    it('should be able to create a gym', async () => {
        const { token } = await createAndAuthenticateUser(app)

        const response = await request(app.server)
        .post('/gyms')
        .set('Authorization', `Bearer ${token}`)
        .send({
            title: 'JavaScript Gym',
            description: 'Some Description',
            phone: '1199999999',
            latitude: -0.0747279,
            longitude: 18.4889672 
        })

        expect(response.statusCode).toEqual(201)
    })
})