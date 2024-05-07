package kyonggiyo.application.candidate.service

import kyonggiyo.application.auth.domain.vo.UserInfo
import kyonggiyo.application.candidate.domain.vo.Status
import kyonggiyo.application.candidate.port.inbound.CandidateResponse
import kyonggiyo.application.candidate.port.inbound.CandidateResponse.Companion.from
import kyonggiyo.application.candidate.port.inbound.LoadCandidateUseCase
import kyonggiyo.application.candidate.port.outbound.LoadCandidatePort
import kyonggiyo.common.response.SliceResponse
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CandidateQueryService(
    private val loadCandidatePort: LoadCandidatePort,
) : LoadCandidateUseCase {

    override fun findAllByStatus(userInfo: UserInfo, status: Status, page: Int): SliceResponse<CandidateResponse> {
        return PageRequest.of(page, DEFAULT_PAGE_SIZE)
            .let { loadCandidatePort.findAllByStatus(status, it) }
            .let { SliceResponse.of(it.map { response -> from(response) }) }
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }

}
