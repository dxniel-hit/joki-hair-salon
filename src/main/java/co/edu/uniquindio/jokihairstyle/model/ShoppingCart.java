package co.edu.uniquindio.jokihairstyle.model;

import co.edu.uniquindio.jokihairstyle.dtos.StoreProductInShoppingCartDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "shoppingCarts")
@RequiredArgsConstructor
public class ShoppingCart {

    @Id private String shoppingCartId;
    private String customerId;
    private List<StoreProductInShoppingCartDTO> productsInShoppingCart;
    private Double totalPrice;
    // TODO Implement: private double totalPriceWithDiscount;
    private String paymentGatewayId; // Will be assigned when a payment is made
    private OrderPayment orderPayment;
}
