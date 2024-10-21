package co.edu.uniquindio.jokihairstyle.dtos;

public record StoreProductInShoppingCartDTO(

        String productName,
        double totalPrice,
        int selectedQty,
        String brandName
) {}
