package kyonggiyo.application.auth.domain.vo;

public record AccessToken(
    String value,
    long expiresIn
){
}
