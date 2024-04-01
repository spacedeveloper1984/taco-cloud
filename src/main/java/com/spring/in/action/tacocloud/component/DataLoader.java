package com.spring.in.action.tacocloud.component;

import com.spring.in.action.tacocloud.dao.IngredientRepository;
import com.spring.in.action.tacocloud.dao.TacoRepository;
import com.spring.in.action.tacocloud.dao.UserRepository;
import com.spring.in.action.tacocloud.model.Ingredient;
import com.spring.in.action.tacocloud.model.IngredientType;
import com.spring.in.action.tacocloud.model.Taco;
import com.spring.in.action.tacocloud.model.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataLoader {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Bean
    public ApplicationRunner commandLineRunner() {
        return args -> {
            final Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP);
            final Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP);
            final Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN);
            final Ingredient carnitas = new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN);
            final Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES);
            final Ingredient lettuce = new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES);
            final Ingredient cheddar = new Ingredient("CHED", "Cheddar", IngredientType.CHEESE);
            final Ingredient jack = new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE);
            final Ingredient salsa = new Ingredient("SLSA", "Salsa", IngredientType.SAUCE);
            final Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE);

            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(tomatoes);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(jack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);

            tacoRepository.save(new Taco()
                    .setName("Carnivore")
                    .setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar))
            );
            tacoRepository.save(new Taco()
                    .setName("Bovine Bounty")
                    .setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream))
            );
            tacoRepository.save(new Taco()
                    .setName("Veg-Out")
                    .setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa))
            );

            userRepository.save(new User("test", encoder.encode("test"), "Test test", "street", "city", "state", "111111", "11111111"));
        };
    }
}
