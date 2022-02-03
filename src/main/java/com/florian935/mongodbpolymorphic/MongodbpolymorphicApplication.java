package com.florian935.mongodbpolymorphic;

import com.florian935.mongodbpolymorphic.domain.DurationExercise;
import com.florian935.mongodbpolymorphic.domain.Exercise;
import com.florian935.mongodbpolymorphic.domain.WeightExercise;
import com.florian935.mongodbpolymorphic.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class MongodbpolymorphicApplication {

	private final ExerciseRepository exerciseRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongodbpolymorphicApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initData() {
		exerciseRepository.deleteAll();
		final List<Exercise> exercises = List.of(
				new Exercise(null, "Bench Press", "exercise"),
				new WeightExercise(null, "Squat", "weight", 100, 10),
				new DurationExercise(null, "Gainage", "duration", 120)
		);

		exerciseRepository.saveAll(exercises);
	}
}
