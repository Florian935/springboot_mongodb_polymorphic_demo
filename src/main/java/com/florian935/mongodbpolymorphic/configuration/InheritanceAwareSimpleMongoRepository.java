package com.florian935.mongodbpolymorphic.configuration;

import org.bson.Document;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@EnableMongoRepositories(repositoryBaseClass = InheritanceAwareSimpleMongoRepository.class)
public class InheritanceAwareSimpleMongoRepository<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> {

    private final MongoOperations mongoOperations;
    private final MongoEntityInformation<T, ID> mongoEntityInformation;
    private final Criteria classCriteria;

    public InheritanceAwareSimpleMongoRepository(MongoEntityInformation<T, ID> mongoEntityInformation,
                                                 MongoOperations mongoOperations) {
        super(mongoEntityInformation, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.mongoEntityInformation = mongoEntityInformation;

        if (mongoEntityInformation.getJavaType().isAnnotationPresent(TypeAlias.class)) {
            classCriteria = where("_class").is(mongoEntityInformation.getJavaType().getAnnotation(TypeAlias.class).value());
        } else {
            classCriteria = null;
        }
    }

    @Override
    public List<T> findAll() {
        return classCriteria != null ? mongoOperations.find(new Query().addCriteria(classCriteria),
                mongoEntityInformation.getJavaType(),
                mongoEntityInformation.getCollectionName())
                : super.findAll();
    }
}
