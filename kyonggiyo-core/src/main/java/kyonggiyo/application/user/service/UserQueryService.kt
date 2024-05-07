package kyonggiyo.application.user.service

import kyonggiyo.application.user.port.inbound.ValidateNicknameUseCase
import kyonggiyo.application.user.port.outbound.ExistUserPort
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
