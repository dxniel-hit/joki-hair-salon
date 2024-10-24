package co.edu.uniquindio.jokihairstyle.controllers;

import co.edu.uniquindio.jokihairstyle.dtos.CreateEmployeeDTO;
import co.edu.uniquindio.jokihairstyle.dtos.UpdateEmployeeDTO;
import co.edu.uniquindio.jokihairstyle.services.interfaces.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/employee")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid CreateEmployeeDTO createEmployeeDTO) {
        return adminService.createEmployee(createEmployeeDTO);
    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable String employeeId,
            @RequestBody @Valid UpdateEmployeeDTO updateEmployeeDTO) {
        return adminService.updateEmployee(employeeId, updateEmployeeDTO);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeeInfo(@PathVariable String employeeId) {
        return adminService.getEmployeeInfo(employeeId);
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String employeeId) {
        return adminService.deleteEmployee(employeeId);
    }

    @PostMapping("/discount/all")
    public ResponseEntity<?> sendDiscountCodeToAllClients() {
        return adminService.sendDiscountCodeToAllClients();
    }

    @PostMapping("/discount/{clientId}")
    public ResponseEntity<?> sendDiscountCodeToOneClient(@PathVariable String clientId) {
        return adminService.sendDiscountCodeToOneClient(clientId);
    }
}

