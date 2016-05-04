package player.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayersList {

    private List<Player> players;

    public PlayersList() {
    }

    public List<Player> getPlayers() {
    	for (int i = 0; i < players.size(); i++){
    		if (players.get(i).getMentions() == null || players.get(i).getMentions().isEmpty() || players.get(i).getMentions().get(0).getRank() > -1)
    			return players;
    		players.get(i).getMentions().get(0).setRank(i+1);
    	}
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
