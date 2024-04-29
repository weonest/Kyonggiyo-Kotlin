package kyonggiyo.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
        @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP(6)")
        var createdAt: LocalDateTime,

        @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP(6)")
        var updatedAt: LocalDateTime
){

//    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP(6)")
//    var createdAt: LocalDateTime? = null
//
//    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP(6)")
//    var updatedAt: LocalDateTime? = null

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }

}
