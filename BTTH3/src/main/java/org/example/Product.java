package org.example;

public class Product {
    private String name;
    private String imagePath;
    private String price;
    private String description;
    private String brand = "Adidas";

    public Product(String name, String imagePath, String price, String description) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.description = description;
        this.brand = "Adidas";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
