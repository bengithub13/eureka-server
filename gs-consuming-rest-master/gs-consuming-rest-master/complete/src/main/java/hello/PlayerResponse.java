package hello;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerResponse {

    private Map<String, List<Player>> body;
    private String statusMessage;

    public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public PlayerResponse() {
    }

    public Map<String, List<Player>> getBody() {
        return body;
    }

    public void setBody(Map<String, List<Player>> body) {
        this.body = body;
    }

}
