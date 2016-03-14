package practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class RestApplication implements CommandLineRunner{


	public static void main(String[] args) {

		SpringApplication.run(RestApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Message quote = restTemplate.getForObject("http://localhost:8082/randomMessage", Message.class);
		System.out.println(quote.toString());
	}
}
