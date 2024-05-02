package kyonggiyo.application.image.port.outbound;

import kyonggiyo.application.image.domain.entity.Image;

import java.util.List;

public interface SaveImagePort {

    List<Image> saveAll(List<Image> images);

    void saveAllInBatch(List<Image> images);

}
