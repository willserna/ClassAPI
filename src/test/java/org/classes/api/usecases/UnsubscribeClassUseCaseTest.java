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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UnsubscribeClassUseCaseTest {

    @Mock
    IGymClassRepository repoMock;

    ModelMapper modelMapper;

    UnsubscribeClassUseCase service;

    @Mock
    GymClassPublisher publisher;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new UnsubscribeClassUseCase(repoMock, modelMapper, publisher);
    }

    @Test
    @DisplayName("unsubscribeClass_Success")
    void unsubscribeClass() throws JsonProcessingException {

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

        var response = service.unsubscribeClass(gymClass.getId(), user.getId());

        StepVerifier.create(response)
                .expectNext(modelMapper.map(gymClass, GymClassDTO.class))
                .verifyComplete();

        Mockito.verify(publisher).publishUnsubscribe(modelMapper.map(gymClass, GymClassDTO.class), user.getId());

    }

}