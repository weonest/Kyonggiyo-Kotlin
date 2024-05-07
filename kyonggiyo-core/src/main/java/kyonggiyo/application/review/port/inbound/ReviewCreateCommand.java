package kyonggiyo.application.review.port.inbound;

import java.util.List;

public record ReviewCreateCommand(
        int rating,
        String content,
        List<String> imageUrls
) {
}