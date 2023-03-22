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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteGymClassUseCaseTest {

    @Mock
    IGymClassRepository repoMock;

    DeleteGymClassUseCase service;

    @BeforeEach
    void init(){

        service = new DeleteGymClassUseCase(repoMock);
    }

    @Test
    @DisplayName("deleteUser_Success")
    void deleteUser(){

        var gymClass = new GymClass("testId", "testClassName", "testCoachName", "testTime", new ArrayList<>());


        Mockito.when(repoMock.findById("testId")).thenReturn(Mono.just(gymClass));
        Mockito.when(repoMock.delete(gymClass)).thenReturn(Mono.empty());

        var response = service.delete("testId");

        StepVerifier.create(response)
                .expectNext("testId")
                .expectComplete()
                .verify();

    }

}