package co.edu.uniquindio.jokihairstyle.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthAdminDTO (

        @NotBlank(message = "Username cannot be null")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String username,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 4, message = "Password must be at least 4 characters long")
        String password
) {}
