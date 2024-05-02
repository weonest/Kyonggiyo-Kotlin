package kyonggiyo.api.adapter.controller.candidate;

import kyonggiyo.api.adapter.controller.candidate.dto.CandidateCreateRequest;
import kyonggiyo.api.adapter.controller.candidate.dto.CandidateUpdateRequest;
import kyonggiyo.application.auth.domain.vo.UserInfo;
import kyonggiyo.application.candidate.domain.vo.Status;
import kyonggiyo.application.candidate.port.inbound.*;
import kyonggiyo.auth.Admin;
import kyonggiyo.auth.Auth;
import kyonggiyo.common.response.SliceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidates")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;
    private final AcceptCandidateUseCase acceptCandidateUseCase;
    private final UpdateCandidateUseCase updateCandidateUseCase;
    private final LoadCandidateUseCase loadCandidateUseCase;
    private final DeleteCandidateUseCase deleteCandidateUseCase;

    @PostMapping
    public ResponseEntity<Void> candidateCreate(@Auth UserInfo userInfo, @RequestBody CandidateCreateRequest request) {
        createCandidateUseCase.createCandidate(userInfo, request.toCommand());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<SliceResponse<CandidateResponse>> candidateListByStatus(@Auth UserInfo userInfo,
                                                                                  @RequestParam Status status,
                                                                                  @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        SliceResponse<CandidateResponse> response = loadCandidateUseCase.findAllByStatus(userInfo, status, page);
        return ResponseEntity.ok(response);
    }

    @Admin
    @PostMapping("/{candidateId}")
    public ResponseEntity<Void> candidateAccept(@PathVariable Long candidateId) {
        acceptCandidateUseCase.acceptCandidate(candidateId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{candidateId}")
    public ResponseEntity<Void> candidateUpdate(@PathVariable Long candidateId, @RequestBody CandidateUpdateRequest request) {

        updateCandidateUseCase.updateCandidate(candidateId, request.toCommand());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<Void> candidateDelete(@Auth UserInfo userInfo, @PathVariable Long candidateId) {
        deleteCandidateUseCase.deleteCandidate(userInfo, candidateId);
        return ResponseEntity.ok().build();
    }

}
