package kyonggiyo.application.candidate.port.inbound

import kyonggiyo.domain.restaurant.RestaurantCategory

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
