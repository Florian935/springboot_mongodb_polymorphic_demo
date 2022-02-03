package com.florian935.mongodbpolymorphic.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@TypeAlias("weight")
@Data
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class WeightExercise extends Exercise {

    Integer weight;
    Integer reps;

    public WeightExercise(String id, String name, Integer weight, Integer reps) {
        super(id, name);
        this.weight = weight;
        this.reps = reps;
    }
}
