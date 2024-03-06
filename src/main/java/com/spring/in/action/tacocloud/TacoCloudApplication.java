package com.spring.in.action.tacocloud;

import com.spring.in.action.tacocloud.dao.IngredientRepository;
import com.spring.in.action.tacocloud.dao.TacoRepository;
import com.spring.in.action.tacocloud.dao.UserRepository;
import com.spring.in.action.tacocloud.model.Ingredient;
import com.spring.in.action.tacocloud.model.IngredientType;
import com.spring.in.action.tacocloud.model.Taco;
import com.spring.in.action.tacocloud.model.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    public ApplicationRunner commandLineRunner(
            IngredientRepository ingredientRepository,
            UserRepository userRepository,
            PasswordEncoder encoder,
            TacoRepository tacoRepository
    ) {
        return args -> {
            Ingredient flourTortilla = new Ingredient(
                    "FLTO", "Flour Tortilla", IngredientType.WRAP);
            Ingredient cornTortilla = new Ingredient(
                    "COTO", "Corn Tortilla", IngredientType.WRAP);
            Ingredient groundBeef = new Ingredient(
                    "GRBF", "Ground Beef", IngredientType.PROTEIN);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", IngredientType.PROTEIN);
            Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES);
            Ingredient lettuce = new Ingredient(
                    "LETC", "Lettuce", IngredientType.VEGGIES);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", IngredientType.CHEESE);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterrey Jack", IngredientType.CHEESE);
            Ingredient salsa = new Ingredient(
                    "SLSA", "Salsa", IngredientType.SAUCE);
            Ingredient sourCream = new Ingredient(
                    "SRCR", "Sour Cream", IngredientType.SAUCE);
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

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepository.save(taco1);
            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepository.save(taco2);
            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
            tacoRepository.save(taco3);
        };
    }

}
