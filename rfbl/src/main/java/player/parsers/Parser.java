package player.parsers;

import java.util.ArrayList;
import java.util.List;

import player.domain.Player;

public abstract class Parser {

	String file;
	List<Player> players;
	PlayerFactory playerFactory;
	
	public Parser(){};
	
	public Parser(String file){
		this.file = file;
		this.players = new ArrayList<>();
		this.playerFactory = new PlayerFactory(this);
	}

	public abstract void parsePlayers();
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
