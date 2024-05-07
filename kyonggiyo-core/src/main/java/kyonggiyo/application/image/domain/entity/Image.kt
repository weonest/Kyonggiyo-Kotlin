package kyonggiyo.application.image.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import kyonggiyo.application.image.domain.vo.ImageType
import kyonggiyo.domain.BaseEntity

@Entity
@Table(name = "images")
class Image(
        key: String,
        imageType: ImageType,
        referenceId: Long
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "upload_key", nullable = false)
    var key: String = key
        protected set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var imageType: ImageType = imageType
        protected set

    @Column(nullable = false)
    var referenceId: Long = referenceId
        protected set

    fun getImageUrl() = URL_PATH + key

    companion object {
        private const val URL_PATH = "https://kyonggiyo-bucket.s3.ap-northeast-2.amazonaws.com/"
    }

}
