package kyonggiyo.application.candidate.port.outbound

import kyonggiyo.application.candidate.domain.entity.Candidate
import kyonggiyo.application.candidate.domain.vo.Status
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface LoadCandidatePort {

    fun getById(id: Long): Candidate

    fun findAllByStatus(status: Status, pageable: Pageable): Slice<Candidate>

}
