package kyonggiyo.application.service.user

import kyonggiyo.application.port.`in`.user.ValidateNicknameUseCase
import kyonggiyo.application.port.out.user.ExistUserPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserQueryService(
    private val existUserPort: ExistUserPort
): ValidateNicknameUseCase {

    override fun existByNickname(nickname: String): Boolean =
        existUserPort.existByNickname(nickname)

}
