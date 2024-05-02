package kyonggiyo.application.image.domain.exception

import kyonggiyo.common.exception.ErrorCode

enum class ImageErrorCode(
        val code: String,
        val message: String
) : ErrorCode {

    INVALID_FILE_EXTENSION("I001", "잘못된 파일 확장자입니다."),
    UPLOAD_EXCEPTION("I002", "이미지 업로드에 실패하였습니다."),
    DELETE_EXCEPTION("I003", "이미지 삭제에 실패하였습니다.");

    override fun code(): String {
        return code
    }

    override fun message(): String {
        return message
    }

}
