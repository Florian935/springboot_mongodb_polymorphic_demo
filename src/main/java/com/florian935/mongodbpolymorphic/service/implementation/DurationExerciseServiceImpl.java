package com.florian935.mongodbpolymorphic.service.implementation;

import com.florian935.mongodbpolymorphic.domain.DurationExercise;
import com.florian935.mongodbpolymorphic.repository.DurationExerciseRepository;
import com.florian935.mongodbpolymorphic.service.DurationExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DurationExerciseServiceImpl implements DurationExerciseService {

    DurationExerciseRepository durationExerciseRepository;

    @Override
    public DurationExercise findById(String id) {

        return durationExerciseRepository.findById(id).get();
    }

    @Override
    public List<DurationExercise> findAll() {

        return durationExerciseRepository
                .findAll()
                .stream()
                .filter(exercise -> exercise.getType().equals("duration"))
                .toList();
    }
}
