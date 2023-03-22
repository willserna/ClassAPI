package org.classes.api.usecases.interfaces;

import org.classes.api.domain.dto.GymClassDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveGymClass {

    Mono<GymClassDTO> save(GymClassDTO gymClassDTO);
}
