package com.florian935.mongodbpolymorphic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PROTECTED;

@Document(collection = "exercise")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = PROTECTED)
public class Exercise {

    @Id
    String id;

    String name;
}
