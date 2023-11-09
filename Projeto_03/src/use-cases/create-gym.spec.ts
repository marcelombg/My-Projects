import { expect, describe, it, beforeEach } from 'vitest'
import { InMemoryGymsRepository } from '@/repositories/in-memory/in-memory-gyms-repository'
import { CreateGymUseCase } from './create-gym'

let gymsRepository: InMemoryGymsRepository
let sut: CreateGymUseCase

describe('Create Use Case', () => {

    beforeEach(() => {
        gymsRepository= new InMemoryGymsRepository()
        sut = new CreateGymUseCase(gymsRepository)
    })

    it('should be able to create gym', async () => {

        const { gym } =  await sut.execute({
            title: 'JavaScript Gym',
            description: null,
            phone: null,
            latitude: -0.0747279,
            longitude: 198.4889672

        })

        expect(gym.id).toEqual(expect.any(String))
    })
})