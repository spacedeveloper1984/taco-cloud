package com.spring.in.action.tacocloud.controller;

import com.spring.in.action.tacocloud.dao.IngredientRepository;
import com.spring.in.action.tacocloud.model.Ingredient;
import com.spring.in.action.tacocloud.model.IngredientType;
import com.spring.in.action.tacocloud.model.Taco;
import com.spring.in.action.tacocloud.model.TacoOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        for (IngredientType type : IngredientType.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(Iterable<Ingredient> ingredients, IngredientType type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(ingredient -> type == ingredient.getType())
                .collect(Collectors.toList());
    }
}
