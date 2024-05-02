package kyonggiyo.application.candidate.port.inbound

import kyonggiyo.application.auth.domain.vo.UserInfo

interface DeleteCandidateUseCase {

    fun deleteCandidate(userInfo: UserInfo, id: Long)

}
