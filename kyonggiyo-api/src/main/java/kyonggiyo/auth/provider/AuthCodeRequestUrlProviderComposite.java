package kyonggiyo.auth.provider;

import kyonggiyo.application.auth.port.inbound.ProvideAuthCodeUrlUseCase;
import kyonggiyo.application.auth.domain.vo.Platform;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AuthCodeRequestUrlProviderComposite implements ProvideAuthCodeUrlUseCase {

    private final Map<Platform, AuthCodeRequestUrlProvider> providerMap;

    public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {
        this.providerMap = providers.stream()
                .collect(Collectors.toUnmodifiableMap(
                        AuthCodeRequestUrlProvider::getPlatform,
                        Function.identity()
                )
        );
    }

    @Override
    public URI provideUri(Platform platform) {
        return providerMap.get(platform).provideUri();
    }

}
