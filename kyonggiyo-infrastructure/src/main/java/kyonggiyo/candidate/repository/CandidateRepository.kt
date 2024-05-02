package kyonggiyo.candidate.repository

import kyonggiyo.application.candidate.domain.entity.Candidate
import kyonggiyo.application.candidate.domain.vo.Status
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface CandidateRepository {

    fun getById(id: Long): Candidate

    fun findAllByStatus(status: Status, pageable: Pageable): Slice<Candidate>

    fun save(candidate: Candidate): Candidate

    fun deleteById(id: Long)

}
