package org.classes.api.usecases;

import lombok.AllArgsConstructor;
import org.classes.api.domain.dto.GymClassDTO;
import org.classes.api.repository.IGymClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllGymClassesUseCase implements Supplier<Flux<GymClassDTO>> {

    private final IGymClassRepository repository;

    private final ModelMapper mapper;

    @Override
    public Flux<GymClassDTO> get() {
        return this.repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(gymClass -> mapper.map(gymClass, GymClassDTO.class));
    }
}
