package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import player.domain.Player;
import player.domain.PlayerResponse;
import player.logger.PlayerLogger;
import player.parsers.Baseball_America_2016_Parser;
import player.parsers.MLB_2016;
import player.parsers.Parser;
import player.repository.PlayerRepository;
import player.utils.ResourceRankPair;

@SpringBootApplication
public class PlayerSearcher implements CommandLineRunner {

    @Autowired()
    private PlayerRepository playerRepository;
	
	private static final Logger log = LoggerFactory.getLogger(PlayerSearcher.class);
    
    @Value("${access_token}")
    private String access_token;
    @Value("${search_url}")
    private String url;

    public static void main(String args[]) {
        SpringApplication.run(PlayerSearcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	List<Parser> parsers = new ArrayList<>();
    	Parser parser1 = new Baseball_America_2016_Parser("baseballamerica2016.txt");
    	parsers.add(parser1);
    	Parser parser2 = new MLB_2016("mlb2016.txt"); 
    	parsers.add(parser2);
    	
    	for (Parser parser : parsers){
    		List<Player> players = parser.getPlayers();
    		search(players);
    	}
    }
    
    public void search(List<Player> players){
        RestTemplate restTemplate = new RestTemplate();
        
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(converter);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
     		.queryParam("response_format", "json")
        .queryParam("league_id", "rfbl2006")
        .queryParam("access_token",access_token)
        .queryParam("name", "corey seager")
        .queryParam("eligible_only", "0")
        .queryParam("version", "3.0")
        .queryParam("free_agents_only", "1");
        
        players
        	.forEach(p -> {
        			builder.replaceQueryParam("name", p.getFullname().trim());
        			PlayerResponse response = restTemplate.getForObject(builder.build().toUriString(), PlayerResponse.class);
        			if (response.getBody().getPlayers().isEmpty()){
        				PlayerLogger.log(p + " was not found");
        			}else{
        				for (Player p2 : response.getBody().getPlayers()){
        					if (p.equals(p2)){
        						Player p3 = playerRepository.findById(p2.getId());
        						if (p3 != null) p2 = p3;
        						if (p2.getMentions() != null && !p2.getMentions().isEmpty()){
        							p2.getMentions().addAll(p.getMentions());
        						}else{
        							p2.setMentions(p.getMentions());
        						}
//        						PlayerLogger.log(p2.toString());
        						playerRepository.save(p2);
        						break;
        					}
        				}
        			}
        	});

    	
    }
 
    
}