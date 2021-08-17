package pangaea.holdings.assessment.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;
import javax.json.JsonString;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("")
public class CallbackResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackResource.class.getName());

    @POST
    @Path("/test1")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response callbackTest1(@NotEmpty JsonObject request){
        if (!validatePayload(request, "test1")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Post isn't for this callback url")
                    .build();
        }
        LOGGER.info(request.toString());

        return Response.ok("Post is meant for this callback url").build();
    }

    @POST
    @Path("/test2")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response callbackTest2(@NotEmpty JsonObject request){
        if (!validatePayload(request, "test2")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Post isn't for this callback url")
                    .build();
        }
        LOGGER.info(request.toString());

        return Response.ok("Post is meant for this callback url").build();
    }

    private boolean validatePayload(@NotEmpty JsonObject request, String topicName) {
        JsonString topic = (JsonString) request.get("topic");
        if (topic == null || topic.getString().isBlank() || !topic.getString().equalsIgnoreCase(topicName)) {
            return false;
        }
        return true;
    }

}
