package kyonggiyo.application.port.in.review.dto;

import java.util.List;

public record ReviewUpdateCommand (
        int rating,
        String content,
        List<String> imageUrls
) {
}