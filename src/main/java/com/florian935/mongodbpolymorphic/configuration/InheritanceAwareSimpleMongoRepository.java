package com.florian935.mongodbpolymorphic.configuration;

import lombok.experimental.FieldDefaults;
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
    Criteria criteria;
    private static final String CLASS_KEY = "_class";
    Query query;

    public InheritanceAwareSimpleMongoRepository(MongoEntityInformation<T, ID> mongoEntityInformation,
                                                 MongoOperations mongoOperations) {
        super(mongoEntityInformation, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.mongoEntityInformation = mongoEntityInformation;
        this.query = new Query();

        this.criteria = buildCriteria();

        if (Objects.nonNull(criteria)) {
            this.query.addCriteria(criteria);
        }
    }

    private Criteria buildCriteria() {
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

        return Objects.nonNull(criteria)
                ? mongoOperations.find(
                        query,
                        mongoEntityInformation.getJavaType(),
                        mongoEntityInformation.getCollectionName())
                : super.findAll();
    }

    @Override
    public void deleteAll() {

        if (Objects.nonNull(criteria)) {

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

        return Objects.nonNull(criteria)
                ? mongoOperations.count(
                        query,
                        mongoEntityInformation.getJavaType(),
                        mongoEntityInformation.getCollectionName())
                : super.count();
    }
}
