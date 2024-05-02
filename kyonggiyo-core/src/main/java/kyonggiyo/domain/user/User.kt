package kyonggiyo.domain.user

import jakarta.persistence.*
import kyonggiyo.domain.BaseEntity

@Entity
@Table(name = "users")
class User(
    nickname: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER
        protected set

    @Column(unique = true, nullable = false)
    var nickname: String = nickname
        protected set

    @Column(nullable = false)
    var isDeleted: Boolean = false
        protected set

    fun delete() {
        if(!isDeleted) {
            isDeleted = true
        }
    }

}
