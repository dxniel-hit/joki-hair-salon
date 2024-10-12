package co.edu.uniquindio.jokihairstyle.services.interfaces;

import co.edu.uniquindio.jokihairstyle.dtos.CreateEmployeeDTO;
import co.edu.uniquindio.jokihairstyle.dtos.UpdateEmployeeDTO;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<?> createEmployee(CreateEmployeeDTO createEmployeeDTO);
    ResponseEntity<?> updateEmployee(String employeeId, UpdateEmployeeDTO updateEmployeeDTO);
    ResponseEntity<?> getEmployeeInfo(String clientId);
    ResponseEntity<?> deleteEmployee(String employeeId);
}
