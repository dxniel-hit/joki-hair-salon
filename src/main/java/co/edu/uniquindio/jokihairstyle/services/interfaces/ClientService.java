package co.edu.uniquindio.jokihairstyle.services.interfaces;

import co.edu.uniquindio.jokihairstyle.model.noncollection.Review;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Services;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ClientService {

    ResponseEntity<?> bookAppointment(String clientId, LocalDateTime date, List<Services> servicesList);
    ResponseEntity<?> cancelAppointment(String clientId);
    ResponseEntity<?> getAvailableHours(LocalDate startDate, LocalDate endDate);
    ResponseEntity<?> leaveReview(String appointmentId, Review review);
    ResponseEntity<?> loadAppointmentHistory(String clientId, int page, int size);
}
