package com.florian935.mongodbpolymorphic.repository;

import com.florian935.mongodbpolymorphic.domain.WeightExercise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightExerciseRepository extends MongoRepository<WeightExercise, String> {
}
