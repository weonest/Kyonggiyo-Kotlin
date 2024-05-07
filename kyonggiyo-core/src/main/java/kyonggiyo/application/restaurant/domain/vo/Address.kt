package kyonggiyo.application.restaurant.domain.vo

import jakarta.persistence.Embeddable

@Embeddable
class Address(
    var address: String,
    var lat: Double,
    var lng: Double,
) {

    init {
        validateCoordinate(lat, lng)
    }


    private fun validateCoordinate(lat: Double, lng: Double) {
        if (lat !in FAR_EAST..FAR_WEST || lng !in FAR_NORTH..FAR_SOUTH) {
            throw IllegalArgumentException("대한민국의 영토가 아닙니다")
        }
    }

    companion object {
        const val FAR_EAST = 131.87222222
        const val FAR_WEST = 125.06666667
        const val FAR_NORTH = 38.45000000
        const val FAR_SOUTH = 33.10000000
    }

}
