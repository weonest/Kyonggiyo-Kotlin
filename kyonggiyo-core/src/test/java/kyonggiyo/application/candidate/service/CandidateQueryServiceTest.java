package kyonggiyo.application.candidate.service;

import kyonggiyo.application.auth.domain.vo.UserInfo;
import kyonggiyo.application.candidate.domain.entity.Candidate;
import kyonggiyo.application.candidate.domain.vo.Status;
import kyonggiyo.application.candidate.port.inbound.CandidateResponse;
import kyonggiyo.application.candidate.port.inbound.LoadCandidateUseCase;
import kyonggiyo.application.candidate.port.outbound.LoadCandidatePort;
import kyonggiyo.application.candidate.service.CandidateQueryService;
import kyonggiyo.application.service.ServiceTest;
import kyonggiyo.application.user.domain.vo.Role;
import kyonggiyo.common.response.SliceResponse;
import kyonggiyo.fixture.CandidateFixtures;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ContextConfiguration(classes = CandidateQueryService.class)
class CandidateQueryServiceTest extends ServiceTest {

    @Autowired
    private LoadCandidateUseCase loadCandidateUseCase;

    @MockBean
    private LoadCandidatePort loadCandidatePort;

    @Test
    void 맛집_후보군의_상태에_따라_조회할_수_있다() {
        // given
        UserInfo userInfo = new UserInfo(1L, Role.USER);
        Status status = Instancio.create(Status.class);
        int page = 10;
        Pageable pageable = PageRequest.of(page, page);

        List<Candidate> candidates = getCandidateListFixture(status);
        SliceImpl<Candidate> slice = new SliceImpl<>(candidates);

        given(loadCandidatePort.findAllByStatus(status, pageable)).willReturn(slice);

        // when
        SliceResponse<CandidateResponse> result = loadCandidateUseCase.findAllByStatus(userInfo, status, page);

        // then
        assertThat(result.data()).hasSameSizeAs(candidates);
    }

    private List<Candidate> getCandidateListFixture(Status status) {
        if (status == Status.WAITING) return CandidateFixtures.generateWaitingCandidateList();
        return CandidateFixtures.generateAcceptedCandidateList();
    }

}
