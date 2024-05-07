package kyonggiyo.application.candidate.port.inbound

import kyonggiyo.application.auth.domain.vo.UserInfo
import kyonggiyo.application.candidate.domain.entity.Candidate
import kyonggiyo.application.restaurant.domain.vo.RestaurantCategory

interface CreateCandidateUseCase {

    fun createCandidate(userInfo: UserInfo, command: CandidateCreateCommand)

}

data class CandidateCreateCommand(
        val name: String,
        val category: RestaurantCategory,
        val contact: String,
        val address: String,
        val lat: Double,
        val lng: Double,
        val reason: String
) {

    fun toEntity(requestId: Long): Candidate {
        return Candidate(
            name = name,
            category = category,
            contact = contact,
            address = address,
            lat = lat,
            lng = lng,
            reason = reason,
            requesterId = requestId
        )
    }

}
