package kyonggiyo.image.repository

import kyonggiyo.application.image.domain.entity.Image
import kyonggiyo.application.image.domain.vo.ImageType

interface ImageRepository {

    fun saveAll(images: List<Image>): List<Image>

    fun findByImageTypeAndReferenceId(imageType: ImageType, referenceId: Long): List<Image>

    fun findByImageTypeAndReferenceIdIn(imageType: ImageType, ids: List<Long>): List<Image>

    fun deleteById(id: Long)

    fun deleteAllByIdInBatch(ids: List<Long>)

}
