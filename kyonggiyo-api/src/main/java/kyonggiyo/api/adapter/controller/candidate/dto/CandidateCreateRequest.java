package kyonggiyo.api.adapter.controller.candidate.dto;

import kyonggiyo.application.candidate.port.inbound.CandidateCreateCommand;
import kyonggiyo.application.restaurant.domain.vo.RestaurantCategory;

public record CandidateCreateRequest(
        String name,
        RestaurantCategory category,
        String contact,
        String address,
        double lat,
        double lng,
        String reason
) {

    public CandidateCreateCommand toCommand() {
        return new CandidateCreateCommand(
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
