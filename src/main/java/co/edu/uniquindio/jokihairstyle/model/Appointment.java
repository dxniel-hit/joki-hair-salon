package co.edu.uniquindio.jokihairstyle.model;

import co.edu.uniquindio.jokihairstyle.model.noncollection.AppointmentStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "appointments")
@RequiredArgsConstructor
public class Appointment {

    @Id private String appointmentId;
    private String clientId; // The client who booked the appointment
    private String employeeId; // The employee providing the service
    private LocalDateTime appointmentDateTime; // Date and time of the appointment
    private String service; // Type of service (e.g., haircut, coloring)
    private double price; // Price of the service
    private AppointmentStatus status; // Status of the appointment (Scheduled, Completed, Canceled)
}

