package player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Value("${access_token}")
    private String access_token;
    
    @Value("${name}")
    private String name;
    
    private String url = "http://rfbl2006.baseball.cbssports.com/api/players/search?"
    		+ "response_format={response_format}&"
    		+ "league_id={league_id}&"
    		+ "access_token={access_token}&"
    		+ "name={name}&"
    		+ "eligible_only={eligible_only}&"
    		+ "version={version}&"
    		+ "free_agents_only={free_agents_only}";

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(converter);
        Map<String, String> urlVars = new HashMap<>();
        urlVars.put("response_format", "json");
        urlVars.put("league_id", "rfbl2006");
        urlVars.put("access_token",access_token);
        urlVars.put("name", name);
        urlVars.put("eligible_only", "0");
        urlVars.put("version", "3.0");
        urlVars.put("free_agents_only", "1");
        
        PlayerResponse response = restTemplate.getForObject(url, PlayerResponse.class, urlVars);

        log.info(response.getBody().getPlayers().get(0).toString());
    }
}