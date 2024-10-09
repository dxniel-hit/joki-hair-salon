package co.edu.uniquindio.jokihairstyle.model.noncollection;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class Schedule {

    private Map<DayOfWeek, TimeRange> workSchedule;
}
