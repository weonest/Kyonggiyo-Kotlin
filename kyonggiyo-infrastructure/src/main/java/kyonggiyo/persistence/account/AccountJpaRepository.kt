package kyonggiyo.persistence.account;

import kyonggiyo.application.auth.domain.entity.Account;
import kyonggiyo.application.auth.domain.vo.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByPlatformAndPlatformId(Platform platform, String platformId);

}
