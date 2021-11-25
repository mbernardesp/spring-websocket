package com.dailycodebuffer.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/hello")
    @SendTo({"/topic/greetings"})
    //@SendTo({"/topic/greetings", "/topic/hi"})
    public Greeting greet(HelloMessage message) {

        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()));
    }

    @MessageMapping("/hi")
    public void hi(HelloMessage message) {

        messagingTemplate.convertAndSend("/topic/hi",new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName())));
    }
}
