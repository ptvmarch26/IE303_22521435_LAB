package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Product> productList = new ArrayList<>();
            productList.add(new Product("4DFWD PULSE SHOES", "images/img1.png", "$160.00", "This product is excluded from all promotional discounts and offers."));
            productList.add(new Product("FORUM MID SHOES", "images/img2.png", "$100.00", "This product is excluded from all promotional discounts and offers."));
            productList.add(new Product("SUPERNOVA SHOES", "images/img3.png", "$150.00", "NMD City Stock 2"));
            productList.add(new Product("Adidas", "images/img4.png", "$160.00","NMD City Stock 2"));
            productList.add(new Product("4DFWD PULSE SHOES", "images/img5.png", "$120.00", "NMD City Stock 2"));
            productList.add(new Product("4DFWD PULSE SHOES", "images/img6.png", "$160.00", "This product is excluded from all promotional discounts and offers."));
            productList.add(new Product("4DFWD PULSE SHOES", "images/img1.png", "$160.00", "This product is excluded from all promotional discounts and offers."));
            productList.add(new Product("FORUM MID SHOES", "images/img2.png", "$100.00", "This product is excluded from all promotional discounts and offers."));

            new AdidasShoeUI(productList).setVisible(true);
        });
    }
}