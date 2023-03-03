package com.example.TutorialIndu.controller;

import com.example.TutorialIndu.Publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    private RabbitMQProducer producer;
    public MessageController(RabbitMQProducer producer){
        this.producer=producer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message")String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ..");

    }

}
