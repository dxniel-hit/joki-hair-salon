package co.edu.uniquindio.jokihairstyle.controllers;

import co.edu.uniquindio.jokihairstyle.dtos.AddProductToShoppingCartDTO;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Review;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Services;
import co.edu.uniquindio.jokihairstyle.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @PostMapping("/{clientId}/book-appointment")
    public ResponseEntity<?> bookAppointment(
            @PathVariable String clientId,
            @RequestParam LocalDateTime date,
            @RequestBody List<Services> servicesList) {
        return clientService.bookAppointment(clientId, date, servicesList);
    }

    @DeleteMapping("/{clientId}/cancel-appointment")
    public ResponseEntity<?> cancelAppointment(@PathVariable String clientId) {
        return clientService.cancelAppointment(clientId);
    }

    @GetMapping("/available-hours")
    public ResponseEntity<?> getAvailableHours(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return clientService.getAvailableHours(startDate, endDate);
    }

    @PostMapping("/{appointmentId}/leave-review")
    public ResponseEntity<?> leaveReview(
            @PathVariable String appointmentId,
            @RequestBody Review review) {
        return clientService.leaveReview(appointmentId, review);
    }

    @GetMapping("/{clientId}/appointment-history")
    public ResponseEntity<?> loadAppointmentHistory(
            @PathVariable String clientId,
            @RequestParam int page,
            @RequestParam int size) {
        return clientService.loadAppointmentHistory(clientId, page, size);
    }

    @GetMapping("/products")
    public ResponseEntity<?> loadProductsPage(
            @RequestParam int page,
            @RequestParam int size) {
        return clientService.loadProductsPage(page, size);
    }

    @PostMapping("/{clientId}/shopping-cart/add-product")
    public ResponseEntity<?> addProductToShoppingCart(
            @PathVariable String clientId,
            @RequestBody AddProductToShoppingCartDTO product) {
        return clientService.addProductToShoppingCart(clientId, product);
    }

    @DeleteMapping("/{clientId}/shopping-cart/delete-product/{productId}")
    public ResponseEntity<?> deleteProductFromShoppingCart(
            @PathVariable String clientId,
            @PathVariable String productId) {
        return clientService.deleteProductFromShoppingCart(clientId, productId);
    }
}
