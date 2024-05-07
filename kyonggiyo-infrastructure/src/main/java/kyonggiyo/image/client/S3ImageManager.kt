package kyonggiyo.image.client

import com.amazonaws.AmazonServiceException
import com.amazonaws.HttpMethod
import com.amazonaws.SdkClientException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import kyonggiyo.application.image.domain.exception.ImageErrorCode
import kyonggiyo.application.image.domain.exception.ImageException
import kyonggiyo.application.image.port.outbound.ImageManager
import kyonggiyo.common.property.AwsProperties
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.text.MessageFormat
import java.util.*
import java.util.concurrent.ThreadLocalRandom

@Component
class S3ImageManager(
        private val s3Client: AmazonS3,
        private val awsProperties: AwsProperties
) : ImageManager {

    private val log = KotlinLogging.logger {}
    private val random = ThreadLocalRandom.current()

    override fun generatePresignedUrl(filename: String): String {
        return with(filename) {
            validateFileExtension(this)
            getGeneratePresignedUrlRequest(this)
        }.let { generatePresignedUrl(it) }
    }

    private fun validateFileExtension(filename: String) {
        StringUtils.getFilenameExtension(filename)
                .let { ImageFileExtension.findExtension(it!!) }
    }

    private fun getGeneratePresignedUrlRequest(filename: String): GeneratePresignedUrlRequest {
        val key = generateImageKey(filename)
        return GeneratePresignedUrlRequest(awsProperties.bucketName, key)
                .withMethod(HttpMethod.PUT)
                .withExpiration(presignedExpiraton())
    }

    private fun generateImageKey(filename: String): String {
        val identifier = random.nextInt(Int.MAX_VALUE).toString()
        return MessageFormat.format(UPLOAD_PATH_FORMAT, identifier, filename)
    }

    private fun presignedExpiraton(): Date {
        return Date().apply {
            time + (1000 * 60 * 2)
        }
    }

    private fun generatePresignedUrl(request: GeneratePresignedUrlRequest): String {
        return try {
            s3Client.generatePresignedUrl(request).toString()
        } catch (e: AmazonServiceException) {
            log.warn(e) { "PresignedUrl 생성에 실패하였습니다. ${e.message}" }
            throw ImageException(ImageErrorCode.UPLOAD_EXCEPTION)
        }
    }

    override fun deleteImage(key: String) {
        try {
            s3Client.deleteObject(awsProperties.bucketName, key)
        } catch (e: SdkClientException) {
            log.warn(e) { "S3 이미지 삭제 작업에 실패하였습니다." }
            throw ImageException(ImageErrorCode.DELETE_EXCEPTION)
        }
    }

    override fun extractImageKey(imageUrl: String): String {
        return imageUrl.replace(URL_PATH, "")
    }

    private enum class ImageFileExtension(val fileExtension: String) {
        SVG("svg"),
        PNG("png"),
        JPG("jpg"),
        JPEG("jpeg");

        companion object {
            fun findExtension(fileExtension: String): ImageFileExtension {
                return values().find { it.name == fileExtension.uppercase() }
                        ?: throw ImageException(ImageErrorCode.INVALID_FILE_EXTENSION)
            }
        }
    }

    companion object {
        private const val UPLOAD_PATH_FORMAT = "public/{0}_{1}"
        private const val URL_PATH = "https://kyonggiyo-bucket.s3.ap-northeast-2.amazonaws.com/"
    }

}
