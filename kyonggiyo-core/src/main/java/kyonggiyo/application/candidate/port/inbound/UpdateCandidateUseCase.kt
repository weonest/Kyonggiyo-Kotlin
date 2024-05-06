package kyonggiyo.application.candidate.port.inbound

import kyonggiyo.application.restaurant.domain.vo.RestaurantCategory

interface UpdateCandidateUseCase {

    fun updateCandidate(candidateId: Long, command: CandidateUpdateCommand)

}

data class CandidateUpdateCommand(
        val name: String,
        val category: RestaurantCategory,
        val contact: String,
        val address: String,
        val lat: Double,
        val lng: Double,
        val reason: String,
)
