package kyonggiyo.application.port.in.candidate;

import kyonggiyo.application.auth.domain.vo.UserInfo;
import kyonggiyo.application.port.in.candidate.dto.CandidateResponse;
import kyonggiyo.common.response.SliceResponse;
import kyonggiyo.domain.candidate.Status;

public interface LoadCandidateUseCase {

    SliceResponse<CandidateResponse> findAllByStatus(UserInfo userInfo, Status status, int page);


}
