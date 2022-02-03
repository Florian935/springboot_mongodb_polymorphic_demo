package com.florian935.mongodbpolymorphic.repository;

import com.florian935.mongodbpolymorphic.domain.DurationExercise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DurationExerciseRepository extends MongoRepository<DurationExercise, String> {
}
