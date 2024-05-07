package kyonggiyo.application.image.port.outbound

interface ImageManager {

    fun generatePresignedUrl(filename: String): String

    fun deleteImage(key: String)

    fun extractImageKey(imageUrl: String): String

}
