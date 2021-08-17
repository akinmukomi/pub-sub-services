package pangaea.holdings.assessment.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pangaea.holdings.assessment.services.PublicationManager;
import pangaea.holdings.assessment.services.SubscriptionManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class PublicationManagerImpl implements PublicationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicationManagerImpl.class);

    @Inject
    SubscriptionManager subscriptionManager;

    private Client client = ClientBuilder.newBuilder().build();

    @Override
    public String publishPost(String topic, JsonObject post) {

        List<String> subscriptions = subscriptionManager.fetchSubscriptions(topic);
        if(subscriptions == null || subscriptions.isEmpty()){
            return "No subscriber for this topic";
        }

        AtomicInteger successfulCounter = new AtomicInteger();

        subscriptions.forEach(subscriberCallbackUrl -> {

            JsonObject request = Json.createObjectBuilder().add("topic", topic)
                    .add("data", post)
                    .build();

            Response response = client.target(subscriberCallbackUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(request));

            int status = response.getStatus();
            if(status == 200){
                LOGGER.info("Successfully published to {} for topic {}", subscriberCallbackUrl, topic);
                successfulCounter.getAndIncrement();
            }else{
                LOGGER.info("Unable to publish to {} for topic {}", subscriberCallbackUrl, topic);
            }
        });
        return "Successfully published to "+successfulCounter.get()+" subscribers";
    }
}
