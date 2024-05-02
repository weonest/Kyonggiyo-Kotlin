package kyonggiyo.application.auth.service

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kyonggiyo.application.auth.port.outbound.LoadAccountPort
import kyonggiyo.application.auth.service.AccountLoginService
import kyonggiyo.application.auth.service.AccountSignUpService
import kyonggiyo.fixture.AccountFixtures
import org.springframework.test.context.ContextConfiguration

@DisplayName("AccountLoginServiceTest")
@ContextConfiguration(classes = [AccountLoginService::class])
class AccountLoginServiceTest : DescribeSpec({
    val loadAccountPort = mockk<LoadAccountPort>()
    val accountSingUpService = mockk<AccountSignUpService>()

    val sut = AccountLoginService(loadAccountPort, accountSingUpService)

    describe("계정 로그인") {
        context("계정이 존재하는 경우") {
            it("계정을 반환한다") {
                // arrange
                val account = AccountFixtures.generateEntity()

                every {
                    loadAccountPort.findByPlatformAndPlatformId(
                            account.platform,
                            account.platformId)
                } returns account

                // act
                val result = sut.login(account.platform, account.platformId)

                // assert
                result shouldBe account
                verify(exactly = 0) { accountSingUpService.signup(any(), any()) }
            }
        }

        context("계정이 존재하지 않는 경우") {
            it("생성한 계정을 반환한다") {
                // arrange
                val account = AccountFixtures.generateEntity()

                every {
                    loadAccountPort.findByPlatformAndPlatformId(
                            account.platform,
                            account.platformId)
                } returns null
                every { accountSingUpService.signup(account.platform, account.platformId) } returns account

                // act
                val result = sut.login(account.platform, account.platformId)

                // assert
                result shouldBe account
                verify(exactly = 1) { accountSingUpService.signup(any(), any()) }
            }
        }
    }

})
