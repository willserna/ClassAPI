package org.classes.api.usecases;

import lombok.AllArgsConstructor;
import org.classes.api.repository.IGymClassRepository;
import org.classes.api.usecases.interfaces.DeleteGymClass;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteGymClassUseCase implements DeleteGymClass {

    private final IGymClassRepository repository;

    @Override
    public Mono<String> delete(String id) {
        return repository.findById(id)
                .flatMap(gymClass -> repository.delete(gymClass).thenReturn(id))
                .switchIfEmpty(Mono.error(new RuntimeException(id)));
    }
}
