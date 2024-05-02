package kyonggiyo.domain.user

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import kyonggiyo.fixture.UserFixtures

@DisplayName("UserTest")
class UserTest : DescribeSpec({

    describe("유저 탈퇴") {
        context("정상 유저인 경우") {
            it("소프트 딜리트 처리한다") {
                // arrange
                val user = UserFixtures.generateEntity()

                // act
                user.delete()

                // assert
                user.isDeleted shouldBe true
            }
        }
    }
})
