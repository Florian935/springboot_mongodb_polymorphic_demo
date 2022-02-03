package com.florian935.mongodbpolymorphic.controller;

import com.florian935.mongodbpolymorphic.domain.Exercise;
import com.florian935.mongodbpolymorphic.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/exercises")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ExerciseController {

    ExerciseService exerciseService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    List<Exercise> findAll() {

        return exerciseService.findAll();
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    void deleteAll() {

        exerciseService.deleteAll();
    }

    @GetMapping(path = "count", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    Long count() {

        return exerciseService.count();
    }
}
