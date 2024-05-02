package kyonggiyo.application.service.auth

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kyonggiyo.application.auth.port.outbound.SaveAccountPort
import kyonggiyo.application.auth.service.AccountSignUpService
import kyonggiyo.fixture.AccountFixtures

@DisplayName("AccountSingUpServiceTest")
class AccountSignUpServiceTest : DescribeSpec({
    val saveAccountPort = mockk<SaveAccountPort>()

    val sut = AccountSignUpService(saveAccountPort)

    describe("계정 생성") {
        context("계정을 생성하고") {
            it("계정을 반환한다") {
                // arrange
                val account = AccountFixtures.generateEntity()

                every { saveAccountPort.save(any()) } returns account

                // act
                val result = sut.signup(account.platform, account.platformId)

                // assert
                result shouldBe account
                verify(exactly = 1) { saveAccountPort.save(any()) }
            }
        }
    }

})
