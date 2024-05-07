package kyonggiyo.api.adapter.controller.candidate.dto;

import kyonggiyo.application.candidate.port.inbound.CandidateUpdateCommand;
import kyonggiyo.application.restaurant.domain.vo.RestaurantCategory;

public record CandidateUpdateRequest(
        String name,
        RestaurantCategory category,
        String contact,
        String address,
        double lat,
        double lng,
        String reason
) {

    public CandidateUpdateCommand toCommand() {
        return new CandidateUpdateCommand(
                name,
                category,
                contact,
                address,
                lat,
                lng,
                reason
        );
    }

}
