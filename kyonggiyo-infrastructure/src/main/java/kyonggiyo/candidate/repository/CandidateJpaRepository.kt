package kyonggiyo.candidate.repository

import kyonggiyo.application.candidate.domain.entity.Candidate
import kyonggiyo.application.candidate.domain.vo.Status
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface CandidateJpaRepository : JpaRepository<Candidate, Long> {
    
    fun findAllByStatus(status: Status, pageable: Pageable): Slice<Candidate>
    
}
