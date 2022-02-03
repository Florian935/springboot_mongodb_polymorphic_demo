package com.florian935.mongodbpolymorphic.service.implementation;

import com.florian935.mongodbpolymorphic.domain.Exercise;
import com.florian935.mongodbpolymorphic.repository.ExerciseRepository;
import com.florian935.mongodbpolymorphic.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ExerciseServiceImpl implements ExerciseService {

    ExerciseRepository exerciseRepository;

    @Override
    public Exercise findById(String id) {

        return exerciseRepository.findById(id).get();
    }

    @Override
    public List<Exercise> findAll() {

        return exerciseRepository.findAll();
    }

    @Override
    public Long count() {

        return exerciseRepository.count();
    }

    @Override
    public void deleteAll() {

        exerciseRepository.deleteAll();
    }
}
