package org.classes.api.usecases;

import org.classes.api.domain.collection.GymClass;
import org.classes.api.repository.IGymClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetAllGymClassesUseCaseTest {

    @Mock
    IGymClassRepository repoMock;

    ModelMapper modelMapper;

    GetAllGymClassesUseCase service;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new GetAllGymClassesUseCase(repoMock, modelMapper);
    }

    @Test
    @DisplayName("getAllClasses_Success")
    void getAllClasses() {
        //Build the scenario you need
        var fluxClasses = Flux.just(new GymClass(), new GymClass());

        Mockito.when(repoMock.findAll()).thenReturn(fluxClasses);

        var response = service.get();

        StepVerifier.create(response)
                .expectNextCount(2)
                .verifyComplete();

        Mockito.verify(repoMock).findAll();

    }

}