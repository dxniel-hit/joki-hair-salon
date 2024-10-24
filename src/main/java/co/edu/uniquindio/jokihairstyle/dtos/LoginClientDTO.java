package co.edu.uniquindio.jokihairstyle.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginClientDTO (
        String username,

        @Size(min = 4, message = "Password must be at least 4 characters long")
        String password
) {}
