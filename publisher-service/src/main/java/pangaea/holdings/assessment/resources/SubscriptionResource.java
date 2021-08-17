package pangaea.holdings.assessment.resources;

import pangaea.holdings.assessment.dtos.requests.CreateSubcriptionRequestDto;
import pangaea.holdings.assessment.dtos.responses.CreateSubscriptionResponseDto;
import pangaea.holdings.assessment.services.SubscriptionManager;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subscribe")
public class SubscriptionResource {

    @Inject
    SubscriptionManager subscriptionManager;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{topic}")
    public Response subscribe(@PathParam("topic") @NotBlank(message = "Topic must be provided") String topic , CreateSubcriptionRequestDto request) {

        CreateSubscriptionResponseDto response = subscriptionManager.createSubscription(topic, request);

        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{topic}")
    public Response fetchSubscriptions(@PathParam("topic") @NotBlank(message = "Topic must be provided") String topic){

        List<String> subscriptions = subscriptionManager.fetchSubscriptions(topic);

        return Response.ok(subscriptions).build();
    }
}