package kyonggiyo.application.image.service

import kyonggiyo.application.image.domain.entity.Image
import kyonggiyo.application.image.domain.vo.ImageType
import kyonggiyo.application.image.port.inbound.DeleteImageUseCase
import kyonggiyo.application.image.port.inbound.ImageDeleteCommand
import kyonggiyo.application.image.port.outbound.DeleteImagePort
import kyonggiyo.application.image.port.outbound.ImageManager
import kyonggiyo.application.image.port.outbound.LoadImagePort
import kyonggiyo.application.image.port.outbound.SaveImagePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ImageService(
        private val imageManager: ImageManager,
        private val saveImagePort: SaveImagePort,
        private val loadImagePort: LoadImagePort,
        private val deleteImagePort: DeleteImagePort
) : DeleteImageUseCase {

    @Transactional
    fun createImages(imageUrls: List<String>, imageType: ImageType, referenceId: Long) {
        imageUrls.map { Image(imageManager.extractImageKey(it), imageType, referenceId) }
                .toList()
                .let { saveImagePort.saveAll(it) }
    }

    @Transactional
    override fun deleteById(command: ImageDeleteCommand) {
        deleteImagePort.deleteById(command.id)
        imageManager.deleteImage(command.key)
    }

    @Transactional
    fun deleteByImageTypeAndReferenceId(imageType: ImageType, referenceId: Long) {
        loadImagePort.findAllByImageTypeAndReferenceId(imageType, referenceId).map { it.id!! }
                .toList()
                .let { deleteImagePort.deleteAllByIdInBatch(it) }
    }

}
