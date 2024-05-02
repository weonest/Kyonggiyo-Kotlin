package kyonggiyo.application.auth.domain.vo;

import kyonggiyo.domain.user.Role;

public record AuthInfo(
        Long userId,
        Role role
) {
}

