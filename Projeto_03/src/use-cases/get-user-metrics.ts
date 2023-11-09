import { CheckInsRepository } from '@/repositories/check-ins-repository'

interface GetUserMetricsUseCaseRequest {
    userId: string
}

interface GetUserMetricsUseCaseResponse {
    checkInsCount: number
}

export class GetUserMetricsUseCase {
    constructor( private checkInRepository: CheckInsRepository ) {}

    async execute({ userId }: GetUserMetricsUseCaseRequest): Promise<GetUserMetricsUseCaseResponse> {

        const checkInsCount = await this.checkInRepository.countByUserId(userId)

        return {
            checkInsCount
        }
    }
}