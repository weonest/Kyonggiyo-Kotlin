package kyonggiyo.application.restaurant.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import kyonggiyo.application.restaurant.domain.vo.Address

class AddressTest : DescribeSpec({

    describe("주소 생성") {
        context("위도와 경도가 대한민국 영토 내에 있으면") {
            it("주소가 생성된다") {
                // arrange
                val address = "경기도 수원시 팔달구 수원천로 210번길 35"
                val latitude = 35.000000
                val longitude = 130.000000

                // act & assert
                shouldNotThrow<IllegalArgumentException> { Address(address, latitude, longitude) }
            }
        }
        context("위도와 경도가 대한민국 영토 내에 없으면") {
            it("IllegalArgumentException 예외가 발생한다") {
                // arrange
                val address = "경기도 수원시 팔달구 수원천로 210번길 35"
                val latitude = 39.000000
                val longitude = 132.000000

                // act & assert
                shouldThrow<IllegalArgumentException> { Address(address, latitude, longitude)  }
            }
        }
    }

})
