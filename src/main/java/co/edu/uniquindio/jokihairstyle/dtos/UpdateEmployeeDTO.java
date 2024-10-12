package co.edu.uniquindio.jokihairstyle.dtos;

import co.edu.uniquindio.jokihairstyle.model.noncollection.Services;

import java.util.List;

public record UpdateEmployeeDTO(

        String completeName,
        List<Services>skills // Adding new abilities the employee has obtained as time passes.
) {}
