package co.edu.uniquindio.jokihairstyle.services.implementation;

import co.edu.uniquindio.jokihairstyle.dtos.CreateEmployeeDTO;
import co.edu.uniquindio.jokihairstyle.dtos.UpdateEmployeeDTO;
import co.edu.uniquindio.jokihairstyle.model.Employee;
import co.edu.uniquindio.jokihairstyle.model.noncollection.EmployeeStatus;
import co.edu.uniquindio.jokihairstyle.repositories.EmployeeRepository;
import co.edu.uniquindio.jokihairstyle.services.interfaces.AdminService;
import co.edu.uniquindio.jokihairstyle.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<?> createEmployee(CreateEmployeeDTO createEmployeeDTO) {
        try {
            // Map DTO values to the Employee object
            Employee newEmployee = new Employee();
            newEmployee.setCompleteName(createEmployeeDTO.completeName());
            newEmployee.setWorkSchedule(createEmployeeDTO.workSchedule());
            newEmployee.setSkills(createEmployeeDTO.skills());
            newEmployee.setHireDate(createEmployeeDTO.hireDate());

            // Initialize other attributes
            newEmployee.setActive(true); // New employees are active by default
            newEmployee.setCurrentStatus(EmployeeStatus.AVAILABLE); // Default status is AVAILABLE
            newEmployee.setReviews(new ArrayList<>()); // Empty list of reviews initially
            newEmployee.setAppointments(new ArrayList<>()); // Empty list of appointments initially

            // Save the employee
            Employee savedEmployee = employeeRepository.save(newEmployee);

            // Return a success response
            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Error", "Failed to update admin", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        return null;
    }


    @Override
    public ResponseEntity<?> deleteEmployee(String employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            ApiResponse<String> response = new ApiResponse<>("Error", "Employee not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Employee employeeToDelete = employee.get();
        employeeToDelete.setActive(false);
        employeeRepository.save(employeeToDelete);

        ApiResponse<String> response = new ApiResponse<>("Success", "Employee deleted", null);
        return new ResponseEntity<>(employeeToDelete, HttpStatus.OK);
    }
}
