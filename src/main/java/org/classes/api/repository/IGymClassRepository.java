package org.classes.api.repository;

import org.classes.api.domain.collection.GymClass;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IGymClassRepository extends ReactiveMongoRepository<GymClass, String> {
}
