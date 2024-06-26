package kyonggiyo.application.review.event.retry;

import kyonggiyo.application.image.service.ImageService;
import kyonggiyo.application.review.service.ReviewEventService;
import kyonggiyo.domain.event.EventCommand;
import kyonggiyo.application.review.event.entity.ReviewEvent;
import kyonggiyo.application.image.domain.vo.ImageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewEventRetryHandler {

    private final ImageService imageService;
    private final ReviewEventService reviewEventService;

    @Transactional
    public void handle(ReviewEvent event) {
        EventCommand command = event.getPayload().getCommand();

        switch (command) {
            case REVIEW_IMAGE_CREATE -> {
                List<String> imageUrls = reviewEventService.convertImageUrlsToList(event.getPayload().getAttribute());
                imageService.createImages(imageUrls, ImageType.REVIEW, event.getPayload().getEntityId());
                reviewEventService.successEvent(event.getId());
            }
        }
    }

}
