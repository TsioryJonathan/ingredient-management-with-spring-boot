package org.spring.ingredientmanagementwithspringboot.entity;

import lombok.*;
import org.spring.ingredientmanagementwithspringboot.entity.DishTypeEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Dish {
    private Integer id;
    private Double price;
    private String name;
    private DishTypeEnum dishType;
    private List<DishIngredient> dishIngredientList;

    public void setDishIngredientList(List<DishIngredient> dishIngredientList) {
        if(this.dishIngredientList != null && !this.dishIngredientList.isEmpty()){
            this.dishIngredientList.clear();
        }
        this.dishIngredientList = dishIngredientList == null ? new ArrayList<>() : dishIngredientList;
        for (DishIngredient dishIngredient : this.dishIngredientList) {
            if(dishIngredient != null){
            dishIngredient.setDish(this);
            }
        }
    }

    public Double getDishCost() {
        double totalPrice = 0;
        for (DishIngredient dishIngredient : dishIngredientList) {
            totalPrice += dishIngredient.getIngredientCost();
        }
        return totalPrice;
    }

    public Double getGrossMargin() {
        if (price == null) {
            throw new RuntimeException("Price is null");
        }
        return price - getDishCost();
    }
}