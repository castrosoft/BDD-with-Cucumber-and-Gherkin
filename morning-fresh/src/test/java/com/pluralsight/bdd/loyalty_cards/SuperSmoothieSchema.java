package com.pluralsight.bdd.loyalty_cards;

import java.util.HashMap;
import java.util.Map;

/**
 * In a real application this might talk to a db or a web service or call
 * some sort of endpoint to interact with the real application
 */

public class SuperSmoothieSchema {

    private Map<String, Integer> pointsPerCategory = new HashMap<>();
    private DrinkCatalog catalog;

    public SuperSmoothieSchema(DrinkCatalog catalog) {
        this.catalog = catalog;
    }

    public void setPointsPerCategory(String category, Integer points) {

        pointsPerCategory.put(category, points);
    }

    public int getPointsFor(String drink) {
        return pointsPerCategory.get(categoryOf(drink));
    }

    private String categoryOf(String drink) {
        return catalog.getCategoryOf(drink);
    }
}
