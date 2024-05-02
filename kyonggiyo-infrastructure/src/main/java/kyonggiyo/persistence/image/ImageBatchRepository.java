package kyonggiyo.persistence.image;

import kyonggiyo.application.image.domain.entity.Image;

import java.util.List;

public interface ImageBatchRepository {

    void saveAllInBatch(List<Image> images);

}
