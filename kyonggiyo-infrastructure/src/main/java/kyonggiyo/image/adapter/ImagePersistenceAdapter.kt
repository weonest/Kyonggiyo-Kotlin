package kyonggiyo.image.adapter

import kyonggiyo.application.image.domain.entity.Image
import kyonggiyo.application.image.domain.vo.ImageType
import kyonggiyo.application.image.port.outbound.DeleteImagePort
import kyonggiyo.application.image.port.outbound.LoadImagePort
import kyonggiyo.application.image.port.outbound.SaveImagePort
import kyonggiyo.image.repository.ImageBatchRepository
import kyonggiyo.image.repository.ImageRepository
import org.springframework.stereotype.Component

@Component
class ImagePersistenceAdapter(
        private val imageRepository: ImageRepository, 
        private val imageBatchRepository: ImageBatchRepository
) : SaveImagePort, LoadImagePort, DeleteImagePort {
   
    override fun saveAll(images: List<Image>): List<Image> {
        return imageRepository.saveAll(images)
    }

    override fun saveAllInBatch(images: List<Image>) {
        imageBatchRepository.saveAllInBatch(images)
    }

    override fun findAllByImageTypeAndReferenceId(imageType: ImageType, referenceId: Long): List<Image> {
        return imageRepository.findByImageTypeAndReferenceId(imageType, referenceId)
    }

    override fun findAllByImageTypeAndReferenceIdIn(imageType: ImageType, ids: List<Long>): List<Image> {
        return imageRepository.findByImageTypeAndReferenceIdIn(imageType, ids)
    }

    override fun deleteById(id: Long) {
        imageRepository.deleteById(id)
    }

    override fun deleteAllByIdInBatch(ids: List<Long>) {
        imageRepository.deleteAllByIdInBatch(ids)
    }
}
