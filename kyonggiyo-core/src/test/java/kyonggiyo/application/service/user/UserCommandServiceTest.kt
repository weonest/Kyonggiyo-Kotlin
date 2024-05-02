package kyonggiyo.application.service.user

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kyonggiyo.application.auth.port.outbound.LoadAccountPort
import kyonggiyo.application.user.port.outbound.LoadUserPort
import kyonggiyo.application.user.port.outbound.SaveUserPort
import kyonggiyo.application.user.service.UserCommandService
import kyonggiyo.fixture.AccountFixtures
import kyonggiyo.fixture.UserFixtures

@DisplayName("UserCommandServiceTest")
class UserCommandServiceTest : DescribeSpec({

    val loadAccountPort = mockk<LoadAccountPort>()
    val saveUserPort = mockk<SaveUserPort>()
    val loadUserPort = mockk<LoadUserPort>()

    val sut = UserCommandService(loadAccountPort, saveUserPort, loadUserPort)

    describe("유저 생성") {
        context("유저 회원가입 요청이 주어지면") {
            it("유저를 생성한다") {
                // arrange
                val account = AccountFixtures.generateEntityWithoutUser()
                val command = UserCreateCommand(account.id!!, "새로운 유저")
                val user = UserFixtures.generateEntity()

                every { loadAccountPort.findById(command.accountId) } returns account
                every { saveUserPort.save(any()) } returns user

                // act
                val result = sut.createUser(command)

                // assert
                result shouldBe account.platform
                verify(exactly = 1) { saveUserPort.save(any()) }
            }
        }
    }

    describe("유저 탈퇴") {
        context("유저 탈퇴 요청이 주어지면") {
            it("유저를 소프트 딜리트 처리한다") {
                // arrange
                val account = AccountFixtures.generateEntity()
                val command = UserDeleteCommand(account.id!!)
                val user = UserFixtures.generateEntity(account.userId!!)

                every { loadAccountPort.findById(command.accountId) } returns account
                every { loadUserPort.getById(user.id!!) } returns user

                // act
                sut.deleteUser(command)

                // assert
                user.isDeleted shouldBe true
            }
        }
    }

})
