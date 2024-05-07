package kyonggiyo.application.candidate.domain.util

import kyonggiyo.application.candidate.domain.vo.Status
import org.springframework.core.convert.converter.Converter

class StatusConverter : Converter<String, Status> {

    override fun convert(status: String): Status {
        return Status.from(status)
    }

}
