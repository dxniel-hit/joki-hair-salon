package co.edu.uniquindio.jokihairstyle.services.interfaces;

import co.edu.uniquindio.jokihairstyle.dtos.AuthAdminDTO;
import co.edu.uniquindio.jokihairstyle.dtos.LoginClientDTO;
import co.edu.uniquindio.jokihairstyle.dtos.RegisterClientDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> loginAdmin(AuthAdminDTO request);
    ResponseEntity<?> registerClient(RegisterClientDTO request);
    ResponseEntity<?> loginClient(LoginClientDTO request);
}
