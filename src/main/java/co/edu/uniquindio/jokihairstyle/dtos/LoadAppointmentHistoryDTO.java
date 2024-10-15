package co.edu.uniquindio.jokihairstyle.dtos;
import co.edu.uniquindio.jokihairstyle.model.noncollection.AppointmentStatus;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Services;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record LoadAppointmentHistoryDTO(

        String employeeName,
        LocalDateTime appointmentDate,
        List<Services> servicesList,
        double price,
        AppointmentStatus appointmentStatus
) {
}

