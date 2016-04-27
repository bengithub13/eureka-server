package parsers;

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

public class Baseball_America_2016_Parser extends Parser{

	public Baseball_America_2016_Parser() {
	}

	public Baseball_America_2016_Parser(String file){
		super(file);
	}
	
	@Override
	public List<String> getPlayers() {
		List<String> players = new ArrayList<String>();
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
			for (String player : lines){
				sb = new StringBuilder();
				Matcher m = pattern.matcher(player.split(",")[0]);
				while (m.find()){
					sb.append(m.group()).append(" ");
				}
				players.add(sb.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return players;
	}

}
