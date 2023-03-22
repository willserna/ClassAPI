package org.classes.api.usecases.interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteGymClass {

    Mono<String> delete(String id);
}
