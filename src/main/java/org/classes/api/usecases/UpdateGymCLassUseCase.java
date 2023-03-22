package org.classes.api.usecases;

import lombok.AllArgsConstructor;
import org.classes.api.domain.collection.GymClass;
import org.classes.api.domain.dto.GymClassDTO;
import org.classes.api.repository.IGymClassRepository;
import org.classes.api.usecases.interfaces.UpdateGymClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UpdateGymCLassUseCase implements UpdateGymClass {

    private final IGymClassRepository repository;

    private final ModelMapper mapper;


    @Override
    public Mono<GymClassDTO> update(String id, GymClassDTO gymClassDTO) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(gymClass -> {
                    gymClassDTO.setId(gymClass.getId());
                    return this.repository.save(mapper.map(gymClassDTO, GymClass.class));
                })
                .map(gymClass -> mapper.map(gymClass, GymClassDTO.class));
    }
}
