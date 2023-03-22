package org.classes.api.usecases;

import lombok.AllArgsConstructor;
import org.classes.api.domain.collection.GymClass;
import org.classes.api.domain.dto.GymClassDTO;
import org.classes.api.repository.IGymClassRepository;
import org.classes.api.usecases.interfaces.SaveGymClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveGymClassUseCase implements SaveGymClass {

    private final IGymClassRepository repository;

    private final ModelMapper mapper;

    @Override
    public Mono<GymClassDTO> save(GymClassDTO gymClassDTO) {
        return repository.save(mapper.map(gymClassDTO, GymClass.class))
                .switchIfEmpty(Mono.empty())
                .map(gymClass -> mapper.map(gymClass, GymClassDTO.class));
    }
}
