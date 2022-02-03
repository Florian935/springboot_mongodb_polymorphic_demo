package com.florian935.mongodbpolymorphic.service;

public interface CrudService<ENTITY, ID> extends ReadOnlyService<ENTITY, ID> {

    void deleteAll();
}
