package org.classes.api.usecases;

import org.classes.api.domain.collection.GymClass;
import org.classes.api.domain.dto.GymClassDTO;
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
class GetGymCLassByIdUseCaseTest {

    @Mock
    IGymClassRepository repoMock;

    ModelMapper modelMapper;

    GetGymCLassByIdUseCase service;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new GetGymCLassByIdUseCase(repoMock, modelMapper);
    }

    @Test
    @DisplayName("getClassByID_Success")
    void getClassByID(){

        var gymClass = new GymClass("testId", "testClassName", "testCoachName", "testTime", new ArrayList<>());


        Mockito.when(repoMock.findById(Mockito.any(String.class))).thenReturn(Mono.just(gymClass));

        var response = service.apply("testId");

        StepVerifier.create(response)
                .expectNext(modelMapper.map(gymClass, GymClassDTO.class))
                .expectComplete()
                .verify();

        Mockito.verify(repoMock).findById("testId");
    }

}