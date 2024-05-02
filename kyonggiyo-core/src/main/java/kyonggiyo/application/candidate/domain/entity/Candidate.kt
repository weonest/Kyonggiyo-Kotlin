package kyonggiyo.application.candidate.domain.entity

import jakarta.persistence.*
import kyonggiyo.application.candidate.domain.vo.Status
import kyonggiyo.domain.BaseEntity
import kyonggiyo.domain.restaurant.Address
import kyonggiyo.domain.restaurant.Restaurant
import kyonggiyo.domain.restaurant.RestaurantCategory

@Entity
@Table(name = "candidates")
class Candidate(
    name: String,
    category: RestaurantCategory,
    contact: String,
    reason: String,
    requesterId: Long,
    address: String,
    lat: Double,
    lng: Double
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    val requesterId: Long = requesterId

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var category: RestaurantCategory = category
        protected set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status = Status.WAITING
        protected set

    @Column(nullable = false)
    @Embedded
    var address = Address(address, lat, lng)
        protected set

    @Column(nullable = false)
    var contact: String = contact
        protected set

    @Column(nullable = false)
    var reason: String = reason
        protected set

    fun accept() {
        this.status = Status.ACCEPTED
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun updateCategory(category: RestaurantCategory) {
        this.category = category
    }

    fun updateContact(contact: String) {
        this.contact = contact
    }

    fun updateAddress(address: String, lat: Double, lng: Double) {
        this.address = Address(address, lat, lng)
    }

    fun updateReason(reason: String) {
        this.reason = reason
    }

    fun toRestaurant(): Restaurant {
        return Restaurant(
            name = name,
            category = category,
            contact = contact,
            address = address,
            lat = address.lat,
            lng = address.lng,
            reason = reason
        )
    }
}
