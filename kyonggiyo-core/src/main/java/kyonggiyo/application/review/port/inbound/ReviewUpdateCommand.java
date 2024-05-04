package kyonggiyo.application.review.port.inbound;

import java.util.List;

public record ReviewUpdateCommand (
        int rating,
        String content,
        List<String> imageUrls
) {
}