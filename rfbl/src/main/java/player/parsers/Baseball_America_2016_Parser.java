package player.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import player.domain.MLBTeam;
import player.domain.Player;
import player.domain.Position;
import player.exceptions.MLBTeamNotFoundException;
import player.exceptions.PositionNotFoundException;
import player.logger.PlayerLogger;

public class Baseball_America_2016_Parser extends Parser{

	public Baseball_America_2016_Parser() {
	}

	public Baseball_America_2016_Parser(String file){
		super(file);
	}
	
	@Override
	public List<Player> getPlayers() {
		List<Player> players = new ArrayList<Player>();
		List<String> lines = new ArrayList<>();
		File file = new File(getClass().getClassLoader().getResource(getFile()).getFile());
		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {

			//1. filter line 3
			//2. convert all content to upper case
			//3. convert it into a List
			Pattern pattern = Pattern.compile("[A-Z][-.a-zA-Z]+");
			lines = stream
//					.filter(line -> !line.startsWith("line3"))
//					.map(String::toUpperCase).
					.collect(Collectors.toList());
			StringBuilder sb = null;
			for (String playerLine : lines){
				Player player = new Player();
				sb = new StringBuilder();
				Matcher m = pattern.matcher(playerLine.split(",")[0]);
				while (m.find()){
					sb.append(m.group()).append(" ");
				}
				player.setFullname(sb.toString().trim());
				player.setEligible_positions(playerLine.split(",")[1].split("/"));
				try {
					player.setPos(Position.getPosition(player.getEligible_positions()[0].trim()));
				}catch(PositionNotFoundException p){
					PlayerLogger.log(p.getMessage());
				}
				player.setPro_team(playerLine.split("[,\"]")[3].trim());
				try {
					MLBTeam.getMLBTeam(player.getPro_team());
				}catch(MLBTeamNotFoundException p){
					PlayerLogger.log(p.getMessage());
				}
				players.add(player);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return players;
	}

}
