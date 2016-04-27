package player.parsers;

import java.util.List;

import player.domain.Player;

public abstract class Parser {

	private String file;
	
	public Parser(){};
	
	public Parser(String file){
		this.file = file;
	}
	
	public abstract List<Player> getPlayers();

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
