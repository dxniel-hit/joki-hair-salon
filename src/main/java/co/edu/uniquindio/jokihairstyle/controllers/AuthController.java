package co.edu.uniquindio.jokihairstyle.controllers;


import co.edu.uniquindio.jokihairstyle.services.interfaces.AuthService;
import co.edu.uniquindio.jokihairstyle.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;


}
