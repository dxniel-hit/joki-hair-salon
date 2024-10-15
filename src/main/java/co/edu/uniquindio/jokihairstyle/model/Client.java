package co.edu.uniquindio.jokihairstyle.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "clients")
@RequiredArgsConstructor
public class Client {

    @Id private String clientId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private List<Appointment> appointmentHistory; // To store previous appointments
    // TODO Add a buying cart attribute.
}

