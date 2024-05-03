package kyonggiyo.image.repository

import kyonggiyo.application.image.domain.entity.Image
import kyonggiyo.application.image.domain.vo.ImageType
import org.springframework.data.jpa.repository.JpaRepository

interface ImageJpaRepository : JpaRepository<Image, Long> {

    fun findByImageTypeAndReferenceId(imageType: ImageType, referenceId: Long): List<Image>

    fun findByImageTypeAndReferenceIdIn(imageType: ImageType, ids: List<Long>): List<Image>

}
