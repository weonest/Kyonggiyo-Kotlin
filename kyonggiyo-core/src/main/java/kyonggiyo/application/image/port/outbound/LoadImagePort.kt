package kyonggiyo.application.image.port.outbound

import kyonggiyo.application.image.domain.entity.Image
import kyonggiyo.application.image.domain.vo.ImageType

interface LoadImagePort {

    fun findAllByImageTypeAndReferenceId(imageType: ImageType, referenceId: Long): List<Image>

    fun findAllByImageTypeAndReferenceIdIn(imageType: ImageType, ids: List<Long>): List<Image>

}
