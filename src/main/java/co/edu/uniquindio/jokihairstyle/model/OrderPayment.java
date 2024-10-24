package co.edu.uniquindio.jokihairstyle.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class OrderPayment {

    @Id
    private String id;
    private LocalDateTime paymentDate;
    private String paymentStatus;
    private String paymentStatusDetails;
    private String paymentType;
    private String paymentCurrency;
    private String authorizationCode;
    private float paymentValue;
}
