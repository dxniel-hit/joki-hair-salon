package co.edu.uniquindio.jokihairstyle.model.noncollection;

import lombok.AllArgsConstructor;
import lombok.Getter;

// First time in my life using this approach.
@AllArgsConstructor
@Getter
public enum Services {
    HAIRCUT(30, 25.0), // 30 minutes, $25
    COLORING(90, 80.0), // 90 minutes, $80
    BEARD(20, 15.0), // 20 minutes, $15
    SHAMPOO(10, 10.0), // 10 minutes, $10
    BLOW_DRY(20, 20.0),
    PERM(120, 100.0),
    HIGHLIGHTS(90, 120.0),
    HAIR_TREATMENT(45, 50.0),
    EXTENSIONS(150, 200.0),
    HAIR_STRAIGHTENING(90, 150.0);

    private final int durationMinutes; // Duration in minutes
    private final double price; // Price of the service
}
