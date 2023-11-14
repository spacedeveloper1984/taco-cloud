package com.spring.in.action.tacocloud.dao.impl;

import com.spring.in.action.tacocloud.model.Ingredient;
import com.spring.in.action.tacocloud.model.IngredientType;
import com.spring.in.action.tacocloud.dao.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class IngredientRepositoryImpl implements IngredientRepository {

    private final static String FIND_ALL = "SELECT id, name, type from Ingredient";
    private final static String FIND_BY_ID = "SELECT id, name, type from Ingredient where id=?";
    private final static String SAVE = "insert into Ingredient (id, name, type) values (?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query(FIND_ALL, this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> ingredients = jdbcTemplate.query(FIND_BY_ID, this::mapRowToIngredient, id);
        return ingredients.size() == 0 ? Optional.empty() : Optional.of(ingredients.get(0));
    }

    @Override
    @Transactional
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(SAVE,
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType()
        );
        return ingredient;
    }


    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                IngredientType.valueOf(resultSet.getString("type"))
        );
    }
}
