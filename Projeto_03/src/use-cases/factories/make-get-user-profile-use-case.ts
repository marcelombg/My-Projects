import { GetUserProfileUseCase } from "../get-user-profile"
import { PrismaUsersRepository } from "@/repositories/prisma/prisma-users-repository"

export function makeGetUserProfileUseCase() {
    const usersRepository = new PrismaUsersRepository()
    const useCase = new GetUserProfileUseCase(usersRepository)

    return useCase
}