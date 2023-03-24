package org.classes.api.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.classes.api.domain.collection.GymClass;
import org.classes.api.domain.dto.GymClassDTO;
import org.classes.api.domain.user.UserDTO;
import org.classes.api.publisher.GymClassPublisher;
import org.classes.api.repository.IGymClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SubscribeClassUseCaseTest {

    @Mock
    IGymClassRepository repoMock;

    ModelMapper modelMapper;

    SubscribeClassUseCase service;

    @Mock
    GymClassPublisher publisher;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new SubscribeClassUseCase(repoMock, modelMapper, publisher);
    }

    @Test
    @DisplayName("subscribeClass_Success")
    void subscribeClass() throws JsonProcessingException {

        var gymClass = new GymClass(
                "testId",
                "testClassName",
                "testCoachName",
                "testTime",
                new ArrayList<>());

        var user = new UserDTO();
        user.setId("userTestId");

        Mockito.when(repoMock.findById(gymClass.getId())).thenReturn(Mono.just(gymClass));
        Mockito.when(repoMock.save(Mockito.any())).thenReturn(Mono.just(gymClass));

        var response = service.subscribeClass(gymClass.getId(), user.getId());

        StepVerifier.create(response)
                .expectNext(modelMapper.map(gymClass, GymClassDTO.class))
                .verifyComplete();

        Mockito.verify(publisher).publishSubscribe(modelMapper.map(gymClass, GymClassDTO.class), user.getId());

    }

}