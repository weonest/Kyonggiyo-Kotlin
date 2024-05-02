package kyonggiyo.persistence.account;

import kyonggiyo.application.auth.port.outbound.LoadAccountPort;
import kyonggiyo.application.auth.port.outbound.SaveAccountPort;
import kyonggiyo.application.auth.domain.entity.Account;
import kyonggiyo.application.auth.domain.vo.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort, SaveAccountPort {

    private final AccountRepository repository;

    @Override
    public Optional<Account> findById(Long accountId) {
        return repository.findById(accountId);
    }

    @Override
    public Optional<Account> findByPlatformAndPlatformId(Platform platform, String platformId) {
        return repository.findByPlatformAndPlatformId(platform, platformId);
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

}
