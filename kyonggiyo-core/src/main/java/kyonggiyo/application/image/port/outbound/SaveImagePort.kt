package kyonggiyo.application.image.port.outbound

import kyonggiyo.application.image.domain.entity.Image

interface SaveImagePort {
    
    fun saveAll(images: List<Image>): List<Image>
    
    fun saveAllInBatch(images: List<Image>)

}
