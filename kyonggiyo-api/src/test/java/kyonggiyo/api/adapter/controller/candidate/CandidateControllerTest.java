package kyonggiyo.api.adapter.controller.candidate;

import kyonggiyo.api.adapter.controller.ControllerTest;
import com.epages.restdocs.apispec.Schema;
import kyonggiyo.api.adapter.controller.candidate.dto.CandidateCreateRequest;
import kyonggiyo.api.adapter.controller.candidate.dto.CandidateUpdateRequest;
import kyonggiyo.application.port.in.candidate.dto.CandidateResponse;
import kyonggiyo.domain.candidate.Status;
import kyonggiyo.application.auth.domain.vo.UserInfo;
import kyonggiyo.common.response.SliceResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CandidateControllerTest extends ControllerTest {

    @Test
    void 유저정보와_요청을_통해_맛집_후보를_생성한다() throws Exception {
        // given
        CandidateCreateRequest request = Instancio.create(CandidateCreateRequest.class);

        willDoNothing().given(createCandidateUseCase).createCandidate(any(UserInfo.class), eq(request.toCommand()));

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/candidates")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(document("create-candidate",
                        resourceDetails().tag("후보").description("후보 생성")
                                        .requestSchema(Schema.schema("CandidateCreateRequest")),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("식당 이름"),
                                fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
                                fieldWithPath("contact").type(JsonFieldType.STRING).description("연락처").optional(),
                                fieldWithPath("address").type(JsonFieldType.STRING).description("주소"),
                                fieldWithPath("lat").type(JsonFieldType.NUMBER).description("위도"),
                                fieldWithPath("lng").type(JsonFieldType.NUMBER).description("경도"),
                                fieldWithPath("reason").type(JsonFieldType.STRING).description("추천 이유")
                        )));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void 맛집_후보를_승인한다() throws Exception {
        // given
        Long candidateId = 1L;

        // when
        ResultActions resultActions = mockMvc.perform(
                        post("/api/v1/candidates/{candidateId}", candidateId)
                                .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN))
                .andDo(document("accept-candidate",
                        resourceDetails().tag("후보").description("후보 승인"),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        )));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void 맛집_후보를_수정한다() throws Exception {
        // given
        Long candidateId = 1L;
        CandidateUpdateRequest request = Instancio.create(CandidateUpdateRequest.class);

        willDoNothing().given(updateCandidateUseCase).updateCandidate(candidateId, request.toCommand());

        // when
        ResultActions resultActions = mockMvc.perform(
                        patch("/api/v1/candidates/{candidateId}", candidateId)
                                .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document("update-candidate",
                        resourceDetails().tag("후보").description("후보 수정")
                                .requestSchema(Schema.schema("CandidateUpdateRequest")),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("식당 이름"),
                                fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
                                fieldWithPath("contact").type(JsonFieldType.STRING).description("연락처").optional(),
                                fieldWithPath("address").type(JsonFieldType.STRING).description("주소"),
                                fieldWithPath("lat").type(JsonFieldType.NUMBER).description("위도"),
                                fieldWithPath("lng").type(JsonFieldType.NUMBER).description("경도"),
                                fieldWithPath("reason").type(JsonFieldType.STRING).description("추천 이유")
                        )));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void 맛집_후보의_등록_상태에_따라_조회한다() throws Exception {
        // given
        int page = 0;
        Status status = Instancio.create(Status.class);
        List<CandidateResponse> candidateResponse = Instancio.ofList(CandidateResponse.class).create();
        SliceResponse<CandidateResponse> response = SliceResponse.of(new SliceImpl<>(candidateResponse));

        given(loadCandidateUseCase.findAllByStatus(any(UserInfo.class), eq(status), eq(page))).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/candidates")
                        .queryParam("status", status.name().toLowerCase())
                        .queryParam("page", "0")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN))
                .andDo(document("find-all-by-status",
                        resourceDetails().tag("후보").description("상태별 조회")
                                .responseSchema(Schema.schema("SliceResponse<CandidateResponse>")),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        ),
                        queryParameters(
                                parameterWithName("status").description("상태"),
                                parameterWithName("page").description("페이지")
                        ),
                        responseFields(
                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("식별자"),
                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("식당 이름"),
                                fieldWithPath("data[].category").type(JsonFieldType.STRING).description("카테고리"),
                                fieldWithPath("data[].contact").type(JsonFieldType.STRING).description("연락처").optional(),
                                fieldWithPath("data[].address").type(JsonFieldType.STRING).description("주소"),
                                fieldWithPath("data[].lat").type(JsonFieldType.NUMBER).description("위도"),
                                fieldWithPath("data[].lng").type(JsonFieldType.NUMBER).description("경도"),
                                fieldWithPath("data[].reason").type(JsonFieldType.STRING).description("추천 이유"),
                                fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("생성일"),
                                fieldWithPath("data[].requesterId").type(JsonFieldType.NUMBER).description("요청자 식별자"),
                                fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("조회된 데이터 수"),
                                fieldWithPath("hasNext").type(JsonFieldType.BOOLEAN).description("다음 페이지 여부")
                        )));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Test
    void 후보를_삭제한다() throws Exception {
        // given
        Long candidateId = 1L;

        willDoNothing().given(deleteCandidateUseCase).deleteCandidate(any(UserInfo.class), eq(candidateId));

        // when
        ResultActions resultActions = mockMvc.perform(
                        delete("/api/v1/candidates/{candidateId}", candidateId)
                                .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN))
                .andDo(document("delete-candidate",
                        resourceDetails().tag("후보").description("후보 삭제")
                                .requestSchema(Schema.schema("CandidateCreateRequest")),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("액세스 토큰")
                        )));

        // then
        resultActions.andExpect(status().isOk());
    }

}
