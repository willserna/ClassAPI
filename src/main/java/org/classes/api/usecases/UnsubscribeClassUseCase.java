package org.classes.api.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.classes.api.domain.dto.GymClassDTO;
import org.classes.api.publisher.GymClassPublisher;
import org.classes.api.repository.IGymClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UnsubscribeClassUseCase {

    private final IGymClassRepository repository;

    private final ModelMapper mapper;
    private final GymClassPublisher classPublisher;

    public Mono<GymClassDTO> unsubscribeClass(String id, String idUser){
        return repository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(gymClass -> {

                    return repository.save(gymClass);
                })
                .map(gymClass -> mapper.map(gymClass, GymClassDTO.class))
                .doOnSuccess(gymClassDTO -> {

                    try {
                        classPublisher.publishUnsubscribe(gymClassDTO,idUser);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
