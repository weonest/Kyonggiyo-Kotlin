package kyonggiyo.application.image.port.inbound

interface DeleteImageUseCase {

    fun deleteById(command: ImageDeleteCommand)

}

data class ImageDeleteCommand(
        val id: Long,
        val key: String
)
