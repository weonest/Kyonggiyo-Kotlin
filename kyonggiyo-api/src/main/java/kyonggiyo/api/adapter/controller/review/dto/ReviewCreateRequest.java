package kyonggiyo.api.adapter.controller.review.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import kyonggiyo.application.review.port.inbound.ReviewCreateCommand;

import java.util.List;

public record ReviewCreateRequest(
        @Min(1) @Max(5)
        int rating,
        @NotBlank(message = "내용은 필수입니다.")
        String content,
        @Nullable
        List<String> imageUrls
) {

    public ReviewCreateCommand toCommand() {
        return new ReviewCreateCommand(
                rating,
                content,
                imageUrls
        );
    }

}
