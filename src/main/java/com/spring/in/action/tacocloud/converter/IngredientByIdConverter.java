package com.spring.in.action.tacocloud.converter;

import com.spring.in.action.tacocloud.dao.IngredientRepository;
import com.spring.in.action.tacocloud.model.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String source) {
        return ingredientRepository.findById(source).orElse(null);
    }
}
