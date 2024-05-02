package kyonggiyo.api.adapter.controller.user

import kyonggiyo.api.adapter.controller.user.dto.UserCreateRequest
import kyonggiyo.api.adapter.controller.user.dto.UserDeleteRequest
import kyonggiyo.api.adapter.controller.user.dto.ValidateNicknameRequest
import kyonggiyo.api.adapter.controller.user.dto.ValidateNicknameResponse
import kyonggiyo.application.auth.port.inbound.ProvideAuthCodeUrlUseCase
import kyonggiyo.application.port.`in`.user.CreateUserUseCase
import kyonggiyo.application.port.`in`.user.ValidateNicknameUseCase
import kyonggiyo.application.port.`in`.user.WithdrawUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users/")
class UserController(
        private val createUserUseCase: CreateUserUseCase,
        private val provideAuthCodeUrlUseCase: ProvideAuthCodeUrlUseCase,
        private val validateNicknameUseCase: ValidateNicknameUseCase,
        private val withdrawUserUseCase: WithdrawUserUseCase,
) {

    @PostMapping("/profile")
    fun userCreate(@RequestBody request: UserCreateRequest): ResponseEntity<Unit> {
        return createUserUseCase.createUser(request.toCommand())
            .let { provideAuthCodeUrlUseCase.provideUri(it) }
            .let { ResponseEntity.created(it).build() }
    }

    @GetMapping("/profile/nickname")
    fun nicknameValidate(request: ValidateNicknameRequest): ResponseEntity<ValidateNicknameResponse> {
        return validateNicknameUseCase.existByNickname(request.nickname)
            .let { ResponseEntity.ok(ValidateNicknameResponse(it)) }
    }

    @DeleteMapping("/profile/withdraw")
    fun withdraw(@RequestBody request: UserDeleteRequest): ResponseEntity<Unit> {
        withdrawUserUseCase.deleteUser(request.toCommand())
        return ResponseEntity.noContent().build()
    }
}