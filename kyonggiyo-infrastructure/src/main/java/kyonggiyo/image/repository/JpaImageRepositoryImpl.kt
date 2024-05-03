package kyonggiyo.image.repository

import kyonggiyo.application.image.domain.entity.Image
import kyonggiyo.application.image.domain.vo.ImageType
import org.springframework.stereotype.Repository

@Repository
class JpaImageRepositoryImpl(
        private val imageJpaRepository: ImageJpaRepository
) : ImageRepository {
    
    override fun saveAll(images: List<Image>): List<Image> {
        return imageJpaRepository.saveAll(images)
    }

    override fun findByImageTypeAndReferenceId(imageType: ImageType, referenceId: Long): List<Image> {
        return imageJpaRepository.findByImageTypeAndReferenceId(imageType, referenceId)
    }

    override fun findByImageTypeAndReferenceIdIn(imageType: ImageType, ids: List<Long>): List<Image> {
        return imageJpaRepository.findByImageTypeAndReferenceIdIn(imageType, ids)
    }

    override fun deleteById(id: Long) {
        imageJpaRepository.deleteById(id)
    }

    override fun deleteAllByIdInBatch(ids: List<Long>) {
        imageJpaRepository.deleteAllByIdInBatch(ids)
    }

}
