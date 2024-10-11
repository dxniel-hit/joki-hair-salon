package co.edu.uniquindio.jokihairstyle.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data; // Generic field for additional data
}
