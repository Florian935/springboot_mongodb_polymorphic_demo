package com.florian935.mongodbpolymorphic.repository;

import com.florian935.mongodbpolymorphic.domain.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends MongoRepository<Exercise, String> {
}
