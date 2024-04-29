package kyonggiyo.entity

import jakarta.persistence.*
import kyonggiyo.domain.user.Role
import java.time.LocalDateTime

// 도메인에서 갖고 있는 생성일
// BaseEntity가 생성일을 다시 Now
@Entity
@Table(name = "users")
class UserEntity(
        @Id
        val id: Long,

        @Enumerated(EnumType.STRING)
        val role: Role,

        @Column(unique = true)
        val nickname: String,

        createdAt: LocalDateTime,

        updateAt: LocalDateTime
) : BaseEntity(createdAt, updateAt) {

    val isDeleted: Boolean = false

}
