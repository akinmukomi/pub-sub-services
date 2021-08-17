package pangaea.holdings.assessment.services.impl;

import io.quarkus.test.Mock;
import pangaea.holdings.assessment.dtos.requests.CreateSubcriptionRequestDto;
import pangaea.holdings.assessment.dtos.responses.CreateSubscriptionResponseDto;
import pangaea.holdings.assessment.services.SubscriptionManager;

import java.util.Arrays;
import java.util.List;

@Mock
public class SubscriptionManagerMock implements SubscriptionManager {

    @Override
    public CreateSubscriptionResponseDto createSubscription(String topic, CreateSubcriptionRequestDto request) {
        return null;
    }

    @Override
    public List<String> fetchSubscriptions(String topic) {
        return Arrays.asList("https://webhook.site/81fa7764-dc5b-467a-a655-8b9ee04e7986");
    }
}
