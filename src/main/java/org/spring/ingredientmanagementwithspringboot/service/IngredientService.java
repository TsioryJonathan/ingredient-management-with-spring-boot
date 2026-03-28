package org.spring.ingredientmanagementwithspringboot.service;

import org.spring.ingredientmanagementwithspringboot.entity.Ingredient;
import org.spring.ingredientmanagementwithspringboot.exception.IngredientNotFoundException;
import org.spring.ingredientmanagementwithspringboot.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findOne(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

}
