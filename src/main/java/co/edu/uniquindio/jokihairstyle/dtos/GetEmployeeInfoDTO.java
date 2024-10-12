package co.edu.uniquindio.jokihairstyle.dtos;

import co.edu.uniquindio.jokihairstyle.model.noncollection.Schedule;

import java.util.List;

public record GetEmployeeInfoDTO(
        String completeName,
        Schedule workSchedule,
        List<String> skills
) {
}
