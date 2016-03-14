package org.bk.consumer.controller;

import org.bk.consumer.domain.Message;
import org.bk.consumer.domain.MessageAcknowledgement;
import org.bk.consumer.feign.PongClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    @Qualifier("ribbonPongClient")
    private PongClient pongClient;

    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    public MessageAcknowledgement printMessage(@RequestBody String string){
    	Message message = new Message();
    	message.setId("1");
    	message.setPayload("hello world");
        return (this.pongClient.sendMessage(message));
    }
//    public MessageAcknowledgement sendMessage(@RequestBody Message message) {
//        return this.pongClient.sendMessage(message);
//    }
}
