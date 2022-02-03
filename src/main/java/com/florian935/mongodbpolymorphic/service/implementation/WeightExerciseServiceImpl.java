package com.florian935.mongodbpolymorphic.service.implementation;

import com.florian935.mongodbpolymorphic.domain.WeightExercise;
import com.florian935.mongodbpolymorphic.repository.WeightExerciseRepository;
import com.florian935.mongodbpolymorphic.service.WeightExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WeightExerciseServiceImpl implements WeightExerciseService {

    WeightExerciseRepository weightExerciseRepository;

    @Override
    public WeightExercise findById(String id) {

        return weightExerciseRepository.findById(id).get();
    }

    @Override
    public List<WeightExercise> findAll() {

        return weightExerciseRepository.findAll();
    }

    @Override
    public Long count() {
        return weightExerciseRepository.count();
    }

    @Override
    public void deleteAll() {

        weightExerciseRepository.deleteAll();
    }
}
