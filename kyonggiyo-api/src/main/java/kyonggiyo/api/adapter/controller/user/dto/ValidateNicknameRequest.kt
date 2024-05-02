package kyonggiyo.api.adapter.controller.user.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ValidateNicknameRequest(
    @field:NotNull
    @field:Size(min = 1, max = 10, message = "닉네임이 너무 길거나 짧습니다.")
    @field:Pattern(regexp = "^[a-zA-Z0-9가-힣]+$", message = "닉네임은 숫자, 한글, 영어만 가능합니다.")
    val nickname: String
)
