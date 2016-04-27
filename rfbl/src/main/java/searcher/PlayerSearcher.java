package searcher;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import parsers.Baseball_America_2016_Parser;
import parsers.MLB_2016;
import parsers.Parser;
import player.PlayerRepository;
import player.PlayerResponse;

@SpringBootApplication
@ComponentScan(basePackages = {"player"})
public class PlayerSearcher implements CommandLineRunner {

    @Autowired()
    private PlayerRepository playerRepository;
	
	private static final Logger log = LoggerFactory.getLogger(PlayerSearcher.class);
    
    @Value("${access_token}")
    private String access_token;
    
    private String url = "http://rfbl2006.baseball.cbssports.com/api/players/search?";

    public static void main(String args[]) {
        SpringApplication.run(PlayerSearcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//    	Parser parser1 = new Baseball_America_2016_Parser("baseballamerica2016.txt");
    	Parser parser2 = new MLB_2016("mlb2016.txt");
    	List<String> players = parser2.getPlayers();
    	search(players);
    }
    
    public void search(List<String> players){
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
        			builder.replaceQueryParam("name", p.trim());
//        			System.out.println(builder.build().toUriString());
        			PlayerResponse response = restTemplate.getForObject(builder.build().toUriString(), PlayerResponse.class);
        			if (response.getBody().getPlayers().isEmpty()){
        				log.info("***" + p + " was not found");
        			}else{
        				System.out.println(response.getBody().getPlayers().get(0).toString());
        				playerRepository.save(response.getBody().getPlayers().get(0));
        			}
        	});

    	
    }
    
}