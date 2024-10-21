package co.edu.uniquindio.jokihairstyle.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "products")
@RequiredArgsConstructor
public class Product {

    @Id private String productId; // Unique identifier for the product
    private String name; // Every name must be unique
    private String description; // Short description of the product
    private double price;
    private int stockQuantity;
    private List<String> categories; // "Hair Care", "Styling", "Treatment"
    private String brand; // Brand of the product (Diamonds they cover my flaws, I got some brand-new type shi)
    private LocalDate dateAdded; // Date when the product was added to the inventory
    private double rating; // Average rating from customer reviews (just integers this time)
    private List<String> images; // URLs or paths to product images. TODO Add Firebase
}
