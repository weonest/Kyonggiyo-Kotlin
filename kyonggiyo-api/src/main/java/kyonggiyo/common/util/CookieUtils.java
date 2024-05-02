package kyonggiyo.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kyonggiyo.application.auth.port.inbound.TokenResponse;
import kyonggiyo.common.exception.AuthenticationException;
import kyonggiyo.common.exception.GlobalErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import java.util.Arrays;

public class CookieUtils {

    private static final String REFRESH_TOKEN = "Refresh-Token";

    private CookieUtils() {
    }

    public static void setCookie(HttpServletResponse httpServletResponse, TokenResponse tokenResponse) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, tokenResponse.getRefreshToken())
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .maxAge(tokenResponse.getRefreshTokenMaxAge())
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public static Cookie getRefreshTokenCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(REFRESH_TOKEN))
                    .findFirst()
                    .orElseThrow(() -> new AuthenticationException(GlobalErrorCode.NO_AUTHENTICATION_INFO_EXCEPTION, "Refresh Cookie가 존재하지 않습니다."));
        }
        throw new AuthenticationException(GlobalErrorCode.NO_AUTHENTICATION_INFO_EXCEPTION, "Cookie가 존재하지 않습니다.");
    }

    public static void removeCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie refreshTokenCookie = getRefreshTokenCookie(httpServletRequest);
        refreshTokenCookie.setMaxAge(0);
        httpServletResponse.addCookie(refreshTokenCookie);
    }

}
