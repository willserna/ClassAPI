package org.classes.api.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.classes.api.domain.dto.GymClassDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GymClassPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    public GymClassPublisher(RabbitTemplate rabbitTemplate, ObjectMapper mapper){

        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    public void publishSubscribe(GymClassDTO classDTO, String id) throws JsonProcessingException{

        String message = mapper.writeValueAsString(new ClassEvent(id, classDTO,"subscribed"));
        rabbitTemplate.convertAndSend("classes-exchange-events", "events.class.routing.key", message);
    }

    public void publishUnsubscribe(GymClassDTO classDTO, String id) throws JsonProcessingException{

        String message = mapper.writeValueAsString(new ClassEvent(id, classDTO,"unsubscribed"));
        rabbitTemplate.convertAndSend("classes-exchange-events", "events.class.routing.key", message);
    }

    public void publishError(Throwable errorEvent){
    }
}
