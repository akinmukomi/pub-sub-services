package pangaea.holdings.assessment.services;

import pangaea.holdings.assessment.dtos.requests.CreateSubcriptionRequestDto;
import pangaea.holdings.assessment.dtos.responses.CreateSubscriptionResponseDto;

import javax.validation.Valid;
import java.util.List;

public interface SubscriptionManager {

    CreateSubscriptionResponseDto createSubscription(String topic, @Valid CreateSubcriptionRequestDto request);

    List<String> fetchSubscriptions(String topic);
}
