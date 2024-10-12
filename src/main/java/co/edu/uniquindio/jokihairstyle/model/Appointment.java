package co.edu.uniquindio.jokihairstyle.model;

import co.edu.uniquindio.jokihairstyle.model.noncollection.AppointmentStatus;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Services;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "appointments")
@RequiredArgsConstructor
public class Appointment {

    @Id private String appointmentId;
    private String clientId; // The client who booked the appointment
    private String employeeId; // The employee providing the service
    private LocalDateTime appointmentDateTime; // Date and time of the appointment
    private List<Services> services; // Type of service (e.g., haircut, coloring)
    private double price; // Price of the service
    private AppointmentStatus status; // Status of the appointment (Scheduled, Completed, Canceled)
}

