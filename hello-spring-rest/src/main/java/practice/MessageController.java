package practice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	public static int id = 0;
	
	@RequestMapping(value = "/pass", method= RequestMethod.GET)
	public Message index(@RequestParam(value="message", defaultValue="") String msg){
		Message message = new Message();
		message.setId(id++);
		message.setMessage(msg);
		return message;
	}
	
}
