package com.spring.in.action.tacocloud.dao;

import com.spring.in.action.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
