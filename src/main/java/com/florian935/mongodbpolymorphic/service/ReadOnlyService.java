package com.florian935.mongodbpolymorphic.service;

import java.util.List;

public interface ReadOnlyService<ENTITY, ID> {

    ENTITY findById(ID id);

    List<ENTITY> findAll();

    Long count();
}
