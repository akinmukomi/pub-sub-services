package pangaea.holdings.assessment.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateSubscriptionResponseDto {

    private String url;

    private String topic;

}
