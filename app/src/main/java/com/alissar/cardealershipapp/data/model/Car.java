package com.alissar.cardealershipapp.data.model;

public class Car {
    private String name;
    private String price;
    // In a real app, you would use a String URL for images
    private int imageResId;

    public Car(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
}