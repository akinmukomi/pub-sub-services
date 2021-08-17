package pangaea.holdings.assessment.services.impl;

import pangaea.holdings.assessment.dtos.requests.CreateSubcriptionRequestDto;
import pangaea.holdings.assessment.dtos.responses.CreateSubscriptionResponseDto;
import pangaea.holdings.assessment.services.SubscriptionManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this implementation of SubscriptionManager, subscriptions are stored in memory.
 * So, a restart of the application would clear all the subscription
 */

@Named(value = "SubscriptionManagerMemoryImpl")
@ApplicationScoped
public class SubscriptionManagerMemoryImpl implements SubscriptionManager {

    private final Map<String, List<String>> subscriptionsStorage = new HashMap<>();

    @Override
    public CreateSubscriptionResponseDto createSubscription(String topic, CreateSubcriptionRequestDto request) {

        List<String> subscriptions = subscriptionsStorage.get(topic);
        if(subscriptions == null){
            subscriptions = new ArrayList<>();
        }
        subscriptions.add(request.getUrl());

        subscriptionsStorage.put(topic, subscriptions);

        return CreateSubscriptionResponseDto.builder().topic(topic).url(request.getUrl()).build();
    }

    @Override
    public List<String> fetchSubscriptions(String topic) {
        if(topic == null || topic.isBlank()){
            return null;
        }
        return this.subscriptionsStorage.get(topic);
    }
}
