package hello;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String url = "http://rfbl2006.baseball.cbssports.com/api/players/search?response_format=json&league_id=rfbl2006&access_token=U2FsdGVkX1_kosbqbuO01smT-5JOob2xW1ltxuEPmYSaWAQV0ZhCBleOidhwgp0NdkTBn88WlFg2N1SCCMVpmuLzgqMgDkdYoMxq0IWdIsPMCoofvChnamlkGwUwHvH7EXqL5UNhftH2fUR_k2PeCQ&name=brian+mccann&eligible_only=0&version=3.0&free_agents_only=1";

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
//        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//        log.info(quote.toString());
        
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);
//        PlayerResponse response = restTemplate.getForObject(url, PlayerResponse.class);
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

//        log.info(response.getBody().getPlayers()[0].toString());
//        log.info(response.getStatusMessage() + response.getBody());
        log.info(response);
    }
}