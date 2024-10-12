package co.edu.uniquindio.jokihairstyle.dtos;

import co.edu.uniquindio.jokihairstyle.model.noncollection.Schedule;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Services;

import java.time.LocalDate;
import java.util.List;

public record CreateEmployeeDTO(
        String completeName,
        Schedule workSchedule,
        List<Services> skills,
        LocalDate hireDate
) {}
