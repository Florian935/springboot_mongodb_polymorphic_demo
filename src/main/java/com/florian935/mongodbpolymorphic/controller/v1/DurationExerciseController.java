package com.florian935.mongodbpolymorphic.controller.v1;

import com.florian935.mongodbpolymorphic.domain.DurationExercise;
import com.florian935.mongodbpolymorphic.service.DurationExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/duration-exercises")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DurationExerciseController {

    DurationExerciseService durationExerciseService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    List<DurationExercise> findAll() {

        return durationExerciseService.findAll();
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    void deleteAll() {

        durationExerciseService.deleteAll();
    }

    @GetMapping(path = "count", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    Long count() {

        return durationExerciseService.count();
    }
}
