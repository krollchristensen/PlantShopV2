package org.example.plantshopv2;

import javafx.scene.image.Image;

/**
 * Represents a plant with a name, price, description, and image.
 */
public class Plant {
    private String name;
    private double price;
    private String description;
    private Image image;

    public Plant(String name, double price, String description, Image image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }
}
