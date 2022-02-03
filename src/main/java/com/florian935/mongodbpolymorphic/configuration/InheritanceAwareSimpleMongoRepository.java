package com.florian935.mongodbpolymorphic.configuration;

import lombok.experimental.FieldDefaults;
import org.bson.Document;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@EnableMongoRepositories(repositoryBaseClass = InheritanceAwareSimpleMongoRepository.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class InheritanceAwareSimpleMongoRepository<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> {

    MongoOperations mongoOperations;
    MongoEntityInformation<T, ID> mongoEntityInformation;
    Document classCriteriaDocument;
    Criteria classCriteria;
    private static final String CLASS_KEY = "_class";
    Query query;

    public InheritanceAwareSimpleMongoRepository(MongoEntityInformation<T, ID> mongoEntityInformation,
                                                 MongoOperations mongoOperations) {
        super(mongoEntityInformation, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.mongoEntityInformation = mongoEntityInformation;
        this.query = new Query();

        this.classCriteria = buildClassCriteria();

        if (Objects.nonNull(classCriteria)) {
            this.query.addCriteria(classCriteria);
        }

        classCriteriaDocument = Objects.nonNull(classCriteria)
                ? classCriteria.getCriteriaObject()
                : new Document();
    }

    private Criteria buildClassCriteria() {
        final boolean isAnnotationPresent = mongoEntityInformation
                .getJavaType()
                .isAnnotationPresent(TypeAlias.class);

        String documentTypeAliasValue = "";

        if (isAnnotationPresent) {
            documentTypeAliasValue = mongoEntityInformation
                    .getJavaType()
                    .getAnnotation(TypeAlias.class)
                    .value();
        }

        return isAnnotationPresent ? where(CLASS_KEY).is(documentTypeAliasValue) : null;
    }

    @Override
    public List<T> findAll() {

        return Objects.nonNull(classCriteria)
                ? mongoOperations.find(
                        query,
                        mongoEntityInformation.getJavaType(),
                        mongoEntityInformation.getCollectionName())
                : super.findAll();
    }

    @Override
    public void deleteAll() {

        if (Objects.nonNull(classCriteria)) {

            mongoOperations.remove(
                    query,
                    mongoEntityInformation.getJavaType(),
                    mongoEntityInformation.getCollectionName());

            return;
        }

        super.deleteAll();
    }

    @Override
    public long count() {

        return this.findAll().size();
    }
}
