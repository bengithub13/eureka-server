import org.bk.consumer.domain.Message;
import org.bk.consumer.domain.MessageAcknowledgement;
import org.bk.consumer.feign.PongClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(PongClient.class)
@WebIntegrationTest
public class testPing {

    private RestTemplate restTemplate;
    Message message;
    
    @Test
	public void test() {
    	message = new Message("1", "Sean");
    	restTemplate = new RestTemplate();
        HttpEntity<Message> requestEntity = new HttpEntity<>(message);
        ResponseEntity<MessageAcknowledgement> response =  this.restTemplate.exchange("http://samplepong/message", HttpMethod.POST, requestEntity, MessageAcknowledgement.class, Maps.newHashMap());
        System.out.println(response.getBody());

    }
    

}
