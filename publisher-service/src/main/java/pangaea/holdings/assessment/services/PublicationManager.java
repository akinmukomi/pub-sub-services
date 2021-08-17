package pangaea.holdings.assessment.services;

import javax.json.JsonObject;
import javax.validation.Valid;

public interface PublicationManager {
    String publishPost(String topic,@Valid JsonObject request);
}
