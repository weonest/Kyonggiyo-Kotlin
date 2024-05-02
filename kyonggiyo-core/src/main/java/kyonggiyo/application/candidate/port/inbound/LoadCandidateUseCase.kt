package kyonggiyo.application.candidate.port.inbound

import kyonggiyo.application.auth.domain.vo.UserInfo
import kyonggiyo.application.candidate.domain.entity.Candidate
import kyonggiyo.application.candidate.domain.vo.Status
import kyonggiyo.common.response.SliceResponse
import kyonggiyo.domain.restaurant.RestaurantCategory
import java.time.LocalDateTime

interface LoadCandidateUseCase {

    fun findAllByStatus(userInfo: UserInfo, status: Status, page: Int): SliceResponse<CandidateResponse>

}

data class CandidateResponse(
    val id: Long,
    val name: String,
    val category: RestaurantCategory,
    val contact: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val reason: String,
    val createdAt: LocalDateTime,
    val requesterId: Long
) {

    companion object {
        fun from(candidate: Candidate): CandidateResponse {
            return CandidateResponse(
                candidate.id!!,
                candidate.name,
                candidate.category,
                candidate.contact,
                candidate.address.address,
                candidate.address.lat,
                candidate.address.lng,
                candidate.reason,
                candidate.createdAt,
                candidate.requesterId
            )
        }
    }

}
