package com.spring.in.action.tacocloud.dao.impl;

import com.spring.in.action.tacocloud.dao.OrderRepository;
import com.spring.in.action.tacocloud.model.Ingredient;
import com.spring.in.action.tacocloud.model.IngredientRef;
import com.spring.in.action.tacocloud.model.Taco;
import com.spring.in.action.tacocloud.model.TacoOrder;
import lombok.AllArgsConstructor;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final static String SAVE_ORDER = "INSERT INTO Taco_Order(delivery_Name, delivery_Street, delivery_City, delivery_State, " +
            "delivery_Zip, cc_number, cc_expiration , cc_cvv, placed_at) VALUES (?,?,?,?,?,?,?,?,?)";
    private final static String SAVE_TACO = "INSERT INTO Taco(name, taco_order, taco_order_key, created_at) VALUES (?,?,?,?)";
    private final static String SAVE_INGREDIENT_REF = "INSERT INTO Ingredient_Ref(ingredient, taco, taco_key) VALUES (?,?,?)";
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        order.setPlacedAt(LocalDate.now());

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(SAVE_ORDER
                , Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                order.getDeliveryName(),
                order.getDeliveryStreet(),
                order.getDeliveryCity(),
                order.getDeliveryState(),
                order.getDeliveryZip(),
                order.getCcNumber(),
                order.getCcExpiration(),
                order.getCcCVV(),
                order.getPlacedAt()
        ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);
        int i = 0;
        for (Taco taco : order.getTacos()) {
            saveTaco(orderId, i++, taco);
        }

        order.setId(orderId);
        return order;
    }

    private void saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(LocalDate.now());

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                SAVE_TACO, Types.VARCHAR, Type.LONG, Type.LONG, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                taco.getName(), orderId, orderKey, taco.getCreatedAt()
        ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        long tacoId = keyHolder.getKey().longValue();
        saveIngredientRefs(tacoId, taco.getIngredients());
        taco.setId(tacoId);
    }

    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredients) {
        int key=0;
        for (IngredientRef ingredientRef : ingredients){
            jdbcTemplate.update(SAVE_INGREDIENT_REF, ingredientRef.getIngredient(), tacoId, key++);
        }
    }
}
