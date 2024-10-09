package co.edu.uniquindio.jokihairstyle.model;

import co.edu.uniquindio.jokihairstyle.model.noncollection.EmployeeStatus;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Schedule;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "employees")
@RequiredArgsConstructor
public class Employee {

    @Id private String employeeId;
    private String completeName;
    private Schedule workSchedule;
    private boolean active; // Means if he is attending to work, not if he is between work schedule.
    private List<String> skills; // Haircuts, coloring, beard, etc.
    private List<String> reviews; // Client given reviews.
    private LocalDate hireDate; // To calculate vacation days
    private EmployeeStatus currentStatus; // On Break, With Client, etc.
    private List<Appointment> appointments;
}
