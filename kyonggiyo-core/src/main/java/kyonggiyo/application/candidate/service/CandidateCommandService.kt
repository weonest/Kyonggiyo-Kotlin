package kyonggiyo.application.candidate.service

import kyonggiyo.application.auth.domain.vo.UserInfo
import kyonggiyo.application.candidate.port.inbound.*
import kyonggiyo.application.candidate.port.outbound.DeleteCandidatePort
import kyonggiyo.application.candidate.port.outbound.LoadCandidatePort
import kyonggiyo.application.candidate.port.outbound.SaveCandidatePort
import kyonggiyo.application.port.out.restaurant.SaveRestaurantPort
import kyonggiyo.application.user.domain.vo.Role
import kyonggiyo.common.exception.ForbiddenException
import kyonggiyo.common.exception.GlobalErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CandidateCommandService(
    private val saveCandidatePort: SaveCandidatePort,
    private val saveRestaurantPort: SaveRestaurantPort,
    private val loadCandidatePort: LoadCandidatePort,
    private val deleteCandidatePort: DeleteCandidatePort,
) : CreateCandidateUseCase, AcceptCandidateUseCase, UpdateCandidateUseCase, DeleteCandidateUseCase {

    override fun createCandidate(userInfo: UserInfo, command: CandidateCreateCommand) {
        command.toEntity(userInfo.userId)
            .let { saveCandidatePort.save(it) }
    }

    override fun updateCandidate(candidateId: Long, command: CandidateUpdateCommand) {
        loadCandidatePort.getById(candidateId)
            .let {
                it.updateName(command.name)
                it.updateCategory(command.category)
                it.updateContact(command.contact)
                it.updateAddress(command.address, command.lat, command.lng)
                it.updateReason(command.reason)
            }
    }

    override fun acceptCandidate(id: Long) {
        loadCandidatePort.getById(id)
            .let {
                it.accept()
                saveRestaurantPort.save(it.toRestaurant())
            }

    }

    override fun deleteCandidate(userInfo: UserInfo, id: Long) {
        val candidate = loadCandidatePort.getById(id)

        if(userInfo.role == Role.ADMIN || candidate.requesterId == userInfo.userId) {
            deleteCandidatePort.deleteById(id)
            return
        }

        throw ForbiddenException(GlobalErrorCode.INVALID_REQUEST_EXCEPTION,
            String.format("유저 식별자 불일치 -> %d", userInfo.userId))
    }

}
