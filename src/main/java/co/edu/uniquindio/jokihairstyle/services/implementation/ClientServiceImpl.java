package co.edu.uniquindio.jokihairstyle.services.implementation;

import co.edu.uniquindio.jokihairstyle.model.Appointment;
import co.edu.uniquindio.jokihairstyle.model.Client;
import co.edu.uniquindio.jokihairstyle.model.Employee;
import co.edu.uniquindio.jokihairstyle.model.noncollection.*;
import co.edu.uniquindio.jokihairstyle.repositories.AppointmentRepository;
import co.edu.uniquindio.jokihairstyle.repositories.ClientRepository;
import co.edu.uniquindio.jokihairstyle.repositories.EmployeeRepository;
import co.edu.uniquindio.jokihairstyle.services.interfaces.ClientService;
import co.edu.uniquindio.jokihairstyle.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final AppointmentRepository appointmentRepository;


    /**
     * Challenging method.
     * @param date LocalDateTime
     * @param servicesList List<Services>
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> bookAppointment(String clientId, LocalDateTime date, List<Services> servicesList) {
        DayOfWeek requestedDay = date.getDayOfWeek();
        LocalTime appointmentStartTime = date.toLocalTime();

        // Calculate the total duration and price of the services requested
        int totalDurationMinutes = servicesList.stream().mapToInt(Services::getDurationMinutes).sum();
        double totalPrice = servicesList.stream().mapToDouble(Services::getPrice).sum();
        LocalTime appointmentEndTime = appointmentStartTime.plusMinutes(totalDurationMinutes);

        // Find available employees based on their EmployeeStatus and if they are active
        List<Employee> availableEmployees = employeeRepository.findAll().stream()
                .filter(employee -> employee.isActive() && employee.getCurrentStatus() == EmployeeStatus.AVAILABLE)
                .toList();

        // Filter employees who are available on the requested date
        List<Employee> employeesAvailableOnDate = availableEmployees.stream()
                .filter(employee -> {
                    Schedule schedule = employee.getWorkSchedule();
                    TimeRange timeRange = schedule.getWorkSchedule().get(requestedDay);
                    if (timeRange == null) return false; // No working hours on this day

                    // Check if the appointment can fit in the employee's work schedule
                    return appointmentStartTime.isAfter(timeRange.getStartTime()) &&
                            appointmentEndTime.isBefore(timeRange.getEndTime());
                })
                .toList();

        // Filter employees who have all the required skills
        List<Employee> matchingEmployees = employeesAvailableOnDate.stream()
                .filter(employee ->  new HashSet<>(employee.getSkills()).containsAll(servicesList))
                .toList();

        if (matchingEmployees.isEmpty()) {
            ApiResponse<String> response = new ApiResponse<>("Success", "No employees available with the required skills at the requested time.", null);
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }

        // Select the first matching employee
        Employee selectedEmployee = matchingEmployees.get(0);

        // Fetch the client that is requesting the appointment
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            ApiResponse<String> response = new ApiResponse<>("Error", "Client not found", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        Client client = clientOptional.get();

        // Create the appointment and save it in the database
        Appointment appointment = createAppointment(date, selectedEmployee, servicesList, totalPrice, clientId);
        appointmentRepository.save(appointment);

        // Add the appointment to the client’s appointment history
        client.getAppointmentHistory().add(appointment);
        clientRepository.save(client); // Save updated client

        // Add the appointment to the employee’s appointment list
        selectedEmployee.getAppointments().add(appointment);
        employeeRepository.save(selectedEmployee); // Save updated employee

        ApiResponse<String> response = new ApiResponse<>("Success", "Appointment booked with " + selectedEmployee.getCompleteName() + " for " + date, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> cancelAppointment(String clientId) {
        // Step 1: Retrieve the client
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            // New cool way to give responses
            return ResponseEntity.status(404).body(new ApiResponse<>("Error", "Client not found", null));
        }
        Client client = clientOptional.get();

        // Step 2: Find the appointment in the client's history
        Optional<Appointment> appointmentOptional = client.getAppointmentHistory().stream().findFirst();

        if (appointmentOptional.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Error", "Appointment not found for this client", null));
        }

        Appointment appointment = appointmentOptional.get();
        String appointmentId = appointment.getAppointmentId();

        // Step 3: Retrieve the employee and remove the appointment from their schedule
        Optional<Employee> employeeOptional = employeeRepository.findById(appointment.getEmployeeId());
        if (employeeOptional.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse<>("Error", "Employee not found", null));
        }

        Employee employee = employeeOptional.get();
        employee.getAppointments().removeIf(a -> a.getAppointmentId().equals(appointmentId));
        employeeRepository.save(employee); // Save updated employee

        // Step 4: Remove the appointment from the client's history
        client.getAppointmentHistory().remove(appointment);
        clientRepository.save(client); // Save updated client

        // Step 5: Delete the appointment from the database
        appointmentRepository.deleteById(appointmentId);

        // Step 6: Return a success response
        return ResponseEntity.ok(new ApiResponse<>("Success", "Appointment canceled successfully", appointment));
    }



    // Method to create the appointment
    private Appointment createAppointment(LocalDateTime date, Employee employee, List<Services> servicesList, double totalPrice, String clientId) {
        Appointment appointment = new Appointment();
        appointment.setClientId(clientId);
        appointment.setEmployeeId(employee.getEmployeeId());
        appointment.setAppointmentDateTime(date);
        appointment.setServices(servicesList);
        appointment.setPrice(totalPrice);
        appointment.setStatus(AppointmentStatus.SCHEDULED); // Default status is Scheduled

        return appointment;
    }
}
