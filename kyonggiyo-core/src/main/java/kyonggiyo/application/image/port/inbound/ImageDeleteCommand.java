package kyonggiyo.application.image.port.inbound;

public record ImageDeleteCommand(
        Long id,
        String key
) {
}
