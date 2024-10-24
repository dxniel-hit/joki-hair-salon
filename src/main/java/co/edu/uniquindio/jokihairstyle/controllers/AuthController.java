package co.edu.uniquindio.jokihairstyle.controllers;


import co.edu.uniquindio.jokihairstyle.dtos.AuthAdminDTO;
import co.edu.uniquindio.jokihairstyle.dtos.LoginClientDTO;
import co.edu.uniquindio.jokihairstyle.dtos.RegisterClientDTO;
import co.edu.uniquindio.jokihairstyle.services.interfaces.AuthService;
import co.edu.uniquindio.jokihairstyle.services.interfaces.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Login for admin
    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody @Valid AuthAdminDTO request) {
        return authService.loginAdmin(request);
    }

    // Register client
    @PostMapping("/client/register")
    public ResponseEntity<?> registerClient(@RequestBody RegisterClientDTO request) {
        return authService.registerClient(request);
    }

    // Login for client
    @PostMapping("/client/login")
    public ResponseEntity<?> loginClient(@RequestBody LoginClientDTO request) {
        return authService.loginClient(request);
    }
}

