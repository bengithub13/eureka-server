package parsers;

import java.util.List;

public abstract class Parser {

	private String file;
	
	public Parser(){};
	
	public Parser(String file){
		this.file = file;
	}
	
	public abstract List<String> getPlayers();

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
