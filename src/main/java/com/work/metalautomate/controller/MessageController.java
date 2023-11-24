package com.work.metalautomate.controller;

import com.work.metalautomate.model.user.Message;
import com.work.metalautomate.model.user.OutputMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("messenger")
public class MessageController {

    private final SimpMessagingTemplate sendingTemplate;

    @Autowired
    public MessageController(SimpMessagingTemplate sendingTemplate) {
        this.sendingTemplate = sendingTemplate;
    }

    @MessageMapping("/chat")
    public void send(Message message) {
        String sendToMessage = "/topic/message/%s";
        String time = new SimpleDateFormat("HH:mm").format(new Date());

        sendingTemplate.convertAndSend(String.format(sendToMessage, message.getTo()),
                new OutputMessage(message.getFrom(), message.getText(), time));
    }

    @GetMapping
    public String index() {
        return "messenger";
    }
}
