package co.edu.uniquindio.jokihairstyle.model.noncollection;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Data
@RequiredArgsConstructor
public class TimeRange {

    private LocalTime startTime;
    private LocalTime endTime;
}
