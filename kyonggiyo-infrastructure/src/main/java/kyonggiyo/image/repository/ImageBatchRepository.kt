package kyonggiyo.image.repository

import kyonggiyo.application.image.domain.entity.Image

interface ImageBatchRepository {

    fun saveAllInBatch(images: List<Image>)

}
