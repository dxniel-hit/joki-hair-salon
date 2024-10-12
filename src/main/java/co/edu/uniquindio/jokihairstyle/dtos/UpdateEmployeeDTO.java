package co.edu.uniquindio.jokihairstyle.dtos;

import java.util.List;

public record UpdateEmployeeDTO(

        String completeName,
        List<String>skills // Adding new abilities the employee has obtained as time passes.
) {}
