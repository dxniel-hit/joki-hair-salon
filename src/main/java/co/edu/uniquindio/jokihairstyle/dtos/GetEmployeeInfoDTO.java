package co.edu.uniquindio.jokihairstyle.dtos;

import co.edu.uniquindio.jokihairstyle.model.noncollection.Schedule;
import co.edu.uniquindio.jokihairstyle.model.noncollection.Services;

import java.util.List;

public record GetEmployeeInfoDTO(
        String completeName,
        Schedule workSchedule,
        List<Services> skills
) {
}
