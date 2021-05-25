package com.pluralsight.bdd.loyalty_cards;

import java.util.HashMap;
import java.util.Map;

/**
 * In a real application this might talk to a db or a web service or call
 * some sort of endpoint to interact with the real application
 */

public class DrinkCatalog {

    Map<String, String> drinkCategories = new HashMap<>();

    public void addDrink(String drink, String category) {
        drinkCategories.put(drink, category);
    }

    public String getCategoryOf(String drink) {
        return drinkCategories.get(drink);
    }
}
