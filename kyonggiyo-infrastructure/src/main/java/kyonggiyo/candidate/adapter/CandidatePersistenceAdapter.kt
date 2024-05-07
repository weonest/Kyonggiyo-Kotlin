package kyonggiyo.candidate.adapter

import kyonggiyo.application.candidate.domain.entity.Candidate
import kyonggiyo.application.candidate.domain.vo.Status
import kyonggiyo.application.candidate.port.outbound.DeleteCandidatePort
import kyonggiyo.application.candidate.port.outbound.LoadCandidatePort
import kyonggiyo.application.candidate.port.outbound.SaveCandidatePort
import kyonggiyo.candidate.repository.CandidateRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Component

@Component
class CandidatePersistenceAdapter(
        private val repository: CandidateRepository
) : LoadCandidatePort, SaveCandidatePort, DeleteCandidatePort {

    override fun getById(id: Long): Candidate {
        return repository.getById(id)
    }

    override fun findAllByStatus(status: Status, pageable: Pageable): Slice<Candidate> {
        return repository.findAllByStatus(status, pageable)
    }

    override fun save(candidate: Candidate): Candidate {
        return repository.save(candidate)
    }

    override fun deleteById(id: Long) {
        repository.deleteById(id)
    }

}
