package kyonggiyo.persistence.image;

import kyonggiyo.application.image.domain.entity.Image;
import kyonggiyo.application.image.domain.vo.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageJpaRepository extends JpaRepository<Image, Long> {

    List<Image> findByImageTypeAndReferenceId(ImageType imageType, Long referenceId);

    List<Image> findByImageTypeAndReferenceIdIn(ImageType imageType, List<Long> ids);

}
