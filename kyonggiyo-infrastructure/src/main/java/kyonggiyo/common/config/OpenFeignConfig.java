package kyonggiyo.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("kyonggiyo.client.oauth")
public class OpenFeignConfig {
}
