package kyonggiyo.domain.auth

import jakarta.persistence.*
import kyonggiyo.common.exception.InvalidStateException
import kyonggiyo.domain.BaseEntity
import kyonggiyo.domain.auth.exception.AccountErrorCode

@Entity
@Table(name = "accounts")
class Account(
        platform: Platform,
        platformId: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var platform: Platform = platform
        protected set

    @Column(nullable = false)
    var platformId: String = platformId
        protected set

    @Column(nullable = true)
    var userId: Long? = null
        protected set

    fun hasNoUser(): Boolean {
        return this.userId == null
    }

    fun registerUser(userId: Long) {
        if (this.userId == null) {
            this.userId = userId
            return
        }
        throw InvalidStateException(AccountErrorCode.ALREADY_HAS_USER);
    }

}
