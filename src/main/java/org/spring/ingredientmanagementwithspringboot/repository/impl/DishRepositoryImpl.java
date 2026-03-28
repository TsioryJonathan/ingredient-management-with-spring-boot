package org.spring.ingredientmanagementwithspringboot.repository.impl;

import org.spring.ingredientmanagementwithspringboot.datasource.Datasource;
import org.spring.ingredientmanagementwithspringboot.entity.Dish;
import org.spring.ingredientmanagementwithspringboot.entity.DishIngredient;
import org.spring.ingredientmanagementwithspringboot.repository.DishRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public class DishRepositoryImpl extends DishRepository {
    private final Datasource datasource;
    public DishRepositoryImpl(Datasource datasource) {
        this.datasource = datasource ;
    }
    @Override
    public List<Dish> findAll() {
        return List.of();
    }

    @Override
    public Dish findOne(int id) {
        return null;
    }

    @Override
    public List<DishIngredient> findIngredientsByDishId(int id) {
        return List.of();
    }
}
