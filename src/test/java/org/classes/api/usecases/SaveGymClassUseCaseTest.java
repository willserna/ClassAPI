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
class SaveGymClassUseCaseTest {

    @Mock
    IGymClassRepository repoMock;

    ModelMapper modelMapper;

    SaveGymClassUseCase service;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new SaveGymClassUseCase(repoMock, modelMapper);
    }

    @Test
    @DisplayName("saveClass_Success")
    void saveClass() {
        //Build the scenario you need
        var gymClass = new GymClass("testId", "testClassName", "testCoachName", "testTime", new ArrayList<>());

        Mockito.when(repoMock.save(gymClass)).thenReturn(Mono.just(gymClass));

        var response = service.save(modelMapper.map(gymClass, GymClassDTO.class));

        StepVerifier.create(response)
                .expectNext(modelMapper.map(gymClass, GymClassDTO.class))
                .expectComplete()
                .verify();

        Mockito.verify(repoMock).save(gymClass);

    }



}