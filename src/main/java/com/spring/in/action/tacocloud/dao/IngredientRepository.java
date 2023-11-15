package com.spring.in.action.tacocloud.dao;

import com.spring.in.action.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
