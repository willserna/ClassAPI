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
class UpdateGymCLassUseCaseTest {

    @Mock
    IGymClassRepository repoMock;

    ModelMapper modelMapper;

    UpdateGymCLassUseCase service;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new UpdateGymCLassUseCase(repoMock, modelMapper);
    }

    @Test
    @DisplayName("updateClass_Success")
    void updateClass() {
        //Build the scenario you need
        var gymClass = new GymClass("testId", "testClassName", "testCoachName", "testTime", new ArrayList<>());
        var gymClassU = new GymClass("testId", "testClassNameUpdate", "testCoachNameUpdate", "testTime", new ArrayList<>());

        Mockito.when(repoMock.findById("testId")).thenReturn(Mono.just(gymClass));
        Mockito.when(repoMock.save(Mockito.any())).thenReturn(Mono.just(gymClassU));

        var response = service.update("testId", modelMapper.map(gymClassU, GymClassDTO.class));

        StepVerifier.create(response)
                .expectNext(modelMapper.map(gymClassU, GymClassDTO.class))
                .expectComplete()
                .verify();

        Mockito.verify(repoMock).save(gymClassU);
        Mockito.verify(repoMock).findById("testId");
    }

}