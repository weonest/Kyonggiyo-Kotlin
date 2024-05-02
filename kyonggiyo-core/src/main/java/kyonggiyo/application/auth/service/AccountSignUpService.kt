package kyonggiyo.application.auth.service;

import kyonggiyo.application.auth.domain.entity.Account;
import kyonggiyo.application.auth.domain.vo.Platform;
import kyonggiyo.application.auth.port.outbound.SaveAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountSignUpService {

    private final SaveAccountPort saveAccountPort;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account signup(Platform platform, String platformId) {
        Account account = new Account(platform, platformId);
        return saveAccountPort.save(account);
    }

}
