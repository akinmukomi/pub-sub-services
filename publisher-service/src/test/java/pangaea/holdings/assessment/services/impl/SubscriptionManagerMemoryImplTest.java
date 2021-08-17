package pangaea.holdings.assessment.services.impl;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pangaea.holdings.assessment.dtos.requests.CreateSubcriptionRequestDto;
import pangaea.holdings.assessment.dtos.responses.CreateSubscriptionResponseDto;
import pangaea.holdings.assessment.services.SubscriptionManager;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;


@QuarkusTest
class SubscriptionManagerMemoryImplTest {

    @Inject
    @Named(value = "SubscriptionManagerMemoryImpl")
    SubscriptionManager subscriptionManager;

    private final String URL = "http://localhost:9000/test1";

    private final String TOPIC = "test1";

    @Test
    void createSubscription() {


        CreateSubscriptionResponseDto response = subscriptionManager.createSubscription(TOPIC, CreateSubcriptionRequestDto.builder().url(URL).build());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getTopic());
        Assertions.assertNotNull(response.getUrl());
        Assertions.assertEquals(response.getUrl(), URL);

    }

    @Test
    void fetchSubscriptions() {

        createSubscription();

        List<String> subscriptions = subscriptionManager.fetchSubscriptions(TOPIC);

        Assertions.assertNotNull(subscriptions);
        Assertions.assertEquals(subscriptions.size(), 1);
        Assertions.assertTrue(subscriptions.stream().anyMatch(url -> url.equalsIgnoreCase(URL)));
    }
}