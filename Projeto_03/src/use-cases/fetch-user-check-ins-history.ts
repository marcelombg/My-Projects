import { CheckIn } from '@prisma/client'
import { CheckInsRepository } from '@/repositories/check-ins-repository'

interface FetchUserCheckInsHistoryUseCaseRequest {
    userId: string,
    page: number
}

interface FetchUserCheckInsHistoryUseCaseResponse {
    checkIns: CheckIn[]
}

export class FetchUserCheckInsHistoryUseCase {
    constructor( private checkInRepository: CheckInsRepository ) {}

    async execute({ userId, page }: FetchUserCheckInsHistoryUseCaseRequest): Promise<FetchUserCheckInsHistoryUseCaseResponse> {

        const checkIns = await this.checkInRepository.findManyByUserId(userId, page)

        return {
            checkIns
        }
    }
}