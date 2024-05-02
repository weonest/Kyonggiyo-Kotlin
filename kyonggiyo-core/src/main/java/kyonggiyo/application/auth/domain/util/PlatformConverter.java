package kyonggiyo.application.auth.domain.util;

import kyonggiyo.application.auth.domain.vo.Platform;
import org.springframework.core.convert.converter.Converter;

public class PlatformConverter implements Converter<String, Platform> {

    @Override
    public Platform convert(String platform) {
        return Platform.from(platform);
    }

}
