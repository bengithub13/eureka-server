package player;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
public class PlayerSearcher implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PlayerSearcher.class);
    
    @Value("${access_token}")
    private String access_token;
    
    private String url = "http://rfbl2006.baseball.cbssports.com/api/players/search?";

    public static void main(String args[]) {
        SpringApplication.run(PlayerSearcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	List<String> players = new ArrayList<String>();
    	players.add("jose altuve");
    	players.add("a.j reed");
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
        .queryParam("name", "altuve")
        .queryParam("eligible_only", "0")
        .queryParam("version", "3.0")
        .queryParam("free_agents_only", "1");
        
        players.stream()
        	.forEach(p -> {
        			builder.replaceQueryParam("name", p);
        			PlayerResponse response = restTemplate.getForObject(builder.build().encode().toUri(), PlayerResponse.class);
        			log.info(response.getBody().getPlayers().get(0).toString());
        	});

    	
    }
    
}