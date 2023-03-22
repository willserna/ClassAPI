package org.classes.api.usecases;

import lombok.AllArgsConstructor;
import org.classes.api.domain.dto.GymClassDTO;
import org.classes.api.repository.IGymClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetGymCLassByIdUseCase implements Function<String, Mono<GymClassDTO>> {

    private final IGymClassRepository repository;

    private final ModelMapper mapper;

    @Override
    public Mono<GymClassDTO> apply(String id) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(gymClass -> mapper.map(gymClass, GymClassDTO.class));
    }
}
