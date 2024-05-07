package kyonggiyo.api.adapter.controller.review.dto;

import kyonggiyo.application.review.port.inbound.ReviewUpdateCommand;

import java.util.List;

public record ReviewUpdateRequest (
        int rating,
        String content,
        List<String> imageUrls
) {

    public ReviewUpdateCommand toCommand() {
        return new ReviewUpdateCommand(
                rating,
                content,
                imageUrls
        );
    }

}
