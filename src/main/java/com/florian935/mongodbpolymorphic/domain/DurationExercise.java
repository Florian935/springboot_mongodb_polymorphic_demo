package com.florian935.mongodbpolymorphic.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@TypeAlias("duration")
@Data
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class DurationExercise extends Exercise {

    Integer duration;

    public DurationExercise(String id, String name, Integer duration) {
        super(id, name);
        this.duration = duration;
    }
}
