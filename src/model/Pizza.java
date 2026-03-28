package model;
import java.util.List;

public class Pizza {
    private String name;
    private List<Ingredient> ingredients;


    public Pizza(String name, List<Ingredient> ingredients) { // constructer
        this.name = name;
        this.ingredients = ingredients;
    }//pizzalar için pizza(pizzaadı,malzemeleri) seklinde kullanılmalı

    public String getName() { return name; }
    public List<Ingredient> getIngredients() { return ingredients; } //getter

    @Override
    public String toString() {
        return name;
    }
}

