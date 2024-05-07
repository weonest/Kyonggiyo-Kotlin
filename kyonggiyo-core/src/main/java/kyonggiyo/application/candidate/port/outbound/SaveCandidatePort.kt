package kyonggiyo.application.candidate.port.outbound

import kyonggiyo.application.candidate.domain.entity.Candidate

interface SaveCandidatePort {

    fun save(candidate: Candidate): Candidate

}
