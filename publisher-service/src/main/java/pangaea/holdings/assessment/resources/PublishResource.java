package pangaea.holdings.assessment.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pangaea.holdings.assessment.services.PublicationManager;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/publish")
public class PublishResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishResource.class.getName());

    @Inject
    PublicationManager publicationManager;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{topic}")
    public Response subscribe(@PathParam("topic") @NotBlank(message = "Topic must be provided") String topic , @NotEmpty JsonObject request) {

        String response = publicationManager.publishPost(topic, request);

        return Response.ok(response).build();
    }
}