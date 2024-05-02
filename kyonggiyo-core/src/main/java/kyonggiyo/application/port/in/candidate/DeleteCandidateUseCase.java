package kyonggiyo.application.port.in.candidate;


import kyonggiyo.application.auth.domain.vo.UserInfo;

public interface DeleteCandidateUseCase {

    void deleteCandidate(UserInfo userInfo, Long id) ;

}
