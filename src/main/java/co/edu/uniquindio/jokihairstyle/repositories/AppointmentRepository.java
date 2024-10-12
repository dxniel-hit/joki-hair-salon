package co.edu.uniquindio.jokihairstyle.repositories;

import co.edu.uniquindio.jokihairstyle.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    // Great
    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);
}
