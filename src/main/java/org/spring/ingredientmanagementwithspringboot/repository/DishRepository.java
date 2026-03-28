package org.spring.ingredientmanagementwithspringboot.repository;

import org.spring.ingredientmanagementwithspringboot.entity.Dish;
import org.spring.ingredientmanagementwithspringboot.entity.DishIngredient;
import org.springframework.stereotype.Repository;

import java.util.List;

public abstract class DishRepository {
    public abstract List<Dish> findAll();
    public abstract Dish findOne(int id);
    public abstract List<DishIngredient> findIngredientsByDishId(int id);

}
