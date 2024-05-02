package kyonggiyo.application.image.port.outbound;

import kyonggiyo.application.image.domain.entity.Image;
import kyonggiyo.application.image.domain.vo.ImageType;

import java.util.List;

public interface LoadImagePort {

    List<Image> findAllByImageTypeAndReferenceId(ImageType imageType, Long referenceId);

    List<Image> findAllByImageTypeAndReferenceIdIn(ImageType imageType, List<Long> ids);
}
