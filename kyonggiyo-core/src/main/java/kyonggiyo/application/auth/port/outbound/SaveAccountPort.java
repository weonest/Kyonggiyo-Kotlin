package kyonggiyo.application.auth.port.outbound;

import kyonggiyo.application.auth.domain.entity.Account;

public interface SaveAccountPort {

    Account save(Account account);

}
