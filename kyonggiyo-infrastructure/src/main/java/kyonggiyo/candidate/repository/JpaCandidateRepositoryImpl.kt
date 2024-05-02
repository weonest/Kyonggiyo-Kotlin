package kyonggiyo.candidate.repository

import kyonggiyo.application.candidate.domain.entity.Candidate
import kyonggiyo.application.candidate.domain.vo.Status
import kyonggiyo.common.exception.GlobalErrorCode
import kyonggiyo.common.exception.NotFoundException
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Repository

@Repository
class JpaCandidateRepositoryImpl(
        private val jpaRepository: CandidateJpaRepository
) : CandidateRepository {

    override fun getById(id: Long): Candidate {
        return jpaRepository.findById(id)
                .orElseThrow { NotFoundException(GlobalErrorCode.NOT_FOUND_ENTITY_EXCEPTION) }
    }

    override fun findAllByStatus(status: Status, pageable: Pageable): Slice<Candidate> {
        return jpaRepository.findAllByStatus(status, pageable)
    }

    override fun save(candidate: Candidate): Candidate {
        return jpaRepository.save(candidate)
    }

    override fun deleteById(id: Long) {
        jpaRepository.deleteById(id)
    }

}
