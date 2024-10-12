package co.edu.uniquindio.jokihairstyle.services.implementation;

import co.edu.uniquindio.jokihairstyle.dtos.CreateEmployeeDTO;
import co.edu.uniquindio.jokihairstyle.dtos.GetEmployeeInfoDTO;
import co.edu.uniquindio.jokihairstyle.dtos.UpdateEmployeeDTO;
import co.edu.uniquindio.jokihairstyle.model.Employee;
import co.edu.uniquindio.jokihairstyle.model.noncollection.EmployeeStatus;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Schedule;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Services;
import co.edu.uniquindio.jokihairstyle.repositories.EmployeeRepository;
import co.edu.uniquindio.jokihairstyle.services.interfaces.AdminService;
import co.edu.uniquindio.jokihairstyle.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
            Employee newEmployee = getNewEmployee(createEmployeeDTO);

            // Save the employee
            employeeRepository.save(newEmployee);

            // Return a success response
            ApiResponse<Employee> response = new ApiResponse<>("Success", "Employee created", newEmployee);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Error", "Failed to create employee", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static Employee getNewEmployee(CreateEmployeeDTO createEmployeeDTO) {
        Employee newEmployee = new Employee();
        newEmployee.setCompleteName(createEmployeeDTO.completeName());
        newEmployee.setWorkSchedule(createEmployeeDTO.workSchedule());
        newEmployee.setSkills(createEmployeeDTO.skills());
        newEmployee.setHireDate(createEmployeeDTO.hireDate());

        // Initialize other attributes
        newEmployee.setActive(true); // New employees are active by default. No email validation this time.
        newEmployee.setCurrentStatus(EmployeeStatus.AVAILABLE); // Default status is AVAILABLE
        newEmployee.setReviews(new ArrayList<>()); // Empty list of reviews initially
        newEmployee.setAppointments(new ArrayList<>()); // Empty list of appointments initially
        return newEmployee;
    }

    /**
     * TODO If we ever decide to implement JWT to this. Update the token here.
     * @param employeeId String
     * @param updateEmployeeDTO UpdateEmployeeDTO
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> updateEmployee(String employeeId, UpdateEmployeeDTO updateEmployeeDTO) {

        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if (employeeOptional.isEmpty()) {
                ApiResponse<String> response = new ApiResponse<>("Error", "Employee not found in database", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Employee found, time to update it.
            Employee employee = employeeOptional.get();

            if (updateEmployeeDTO.completeName() != null) {
                employee.setCompleteName(updateEmployeeDTO.completeName());
            }
            if (updateEmployeeDTO.skills() != null) {
                employee.setSkills(updateEmployeeDTO.skills());
            }

            employeeRepository.save(employee);

            ApiResponse<Employee> response = new ApiResponse<>("Success","Client update done", employee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Error","Could not update employee", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getEmployeeInfo(String clientId) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(clientId);
            if (employeeOptional.isPresent()) {
                ApiResponse<GetEmployeeInfoDTO> response = getCreateEmployeeDTOApiResponse(employeeOptional);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<String> response = new ApiResponse<>("Error", "Client info not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Error", "Failed to retrieve client info", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Method generated by IntelliJ
    private static ApiResponse<GetEmployeeInfoDTO> getCreateEmployeeDTOApiResponse(Optional<Employee> employee) {
        String completeName = employee.get().getCompleteName();
        Schedule workSchedule = employee.get().getWorkSchedule();
        List<Services> skills = employee.get().getSkills();

        GetEmployeeInfoDTO dto = new GetEmployeeInfoDTO(completeName, workSchedule, skills);
        return new ApiResponse<>("Success", "Client info returned", dto);
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
