package com.spring.in.action.tacocloud.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Ingredient {

    @Id
    private String id;
    private String name;
    private IngredientType type;
}
