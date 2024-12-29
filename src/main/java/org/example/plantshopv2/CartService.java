package org.example.plantshopv2;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Service for handling cart operations.
 */
public class CartService {
    private final ObservableList<Plant> cart = FXCollections.observableArrayList();
    private double totalPrice = 0.0;

    public ObservableList<Plant> getCart() {
        return cart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addPlantToCart(Plant plant) {
        cart.add(plant);
        totalPrice += plant.getPrice();
    }

    public void clearCart() {
        cart.clear();
        totalPrice = 0.0;
    }
}
