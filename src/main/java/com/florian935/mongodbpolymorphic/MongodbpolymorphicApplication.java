package com.florian935.mongodbpolymorphic;

import com.florian935.mongodbpolymorphic.configuration.InheritanceAwareSimpleMongoRepository;
import com.florian935.mongodbpolymorphic.domain.DurationExercise;
import com.florian935.mongodbpolymorphic.domain.Exercise;
import com.florian935.mongodbpolymorphic.domain.WeightExercise;
import com.florian935.mongodbpolymorphic.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@EnableMongoRepositories(repositoryBaseClass = InheritanceAwareSimpleMongoRepository.class)
public class MongodbpolymorphicApplication {

	private final ExerciseRepository exerciseRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongodbpolymorphicApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initData() {

		exerciseRepository.deleteAll();
		final List<Exercise> exercises = List.of(
				new Exercise(null, "Bench Press"),
				new WeightExercise(null, "Squat", 100, 10),
				new DurationExercise(null, "Gainage", 120)
		);

		exerciseRepository.saveAll(exercises);
	}
}
