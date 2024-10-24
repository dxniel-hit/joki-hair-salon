package co.edu.uniquindio.jokihairstyle.services.implementation;

import co.edu.uniquindio.jokihairstyle.dtos.AuthAdminDTO;
import co.edu.uniquindio.jokihairstyle.dtos.LoginClientDTO;
import co.edu.uniquindio.jokihairstyle.dtos.RegisterClientDTO;
import co.edu.uniquindio.jokihairstyle.model.Admin;
import co.edu.uniquindio.jokihairstyle.model.Client;
import co.edu.uniquindio.jokihairstyle.repositories.AdminRepository;
import co.edu.uniquindio.jokihairstyle.repositories.ClientRepository;
import co.edu.uniquindio.jokihairstyle.services.interfaces.AuthService;
import co.edu.uniquindio.jokihairstyle.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    @Override
    public ResponseEntity<?> loginAdmin(AuthAdminDTO request) {
        try {
            String username = request.username();
            String password = request.password();
            Optional<Admin> adminDB = adminRepository.findByUsername(username);
            if (adminDB.isPresent()) {
                if (password.equals(adminDB.get().getPassword())) {
                    ApiResponse<Admin> response = new ApiResponse<>("Success", "Logged in", adminDB.get());
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                } else {
                    ApiResponse<String> response = new ApiResponse<>("Error", "Invalid username or password", null);
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                ApiResponse<String> response = new ApiResponse<>("Error", "Account not found in database", null);
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApiResponse<String> response = new ApiResponse<>("Error", "Login logic failed", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> registerClient(RegisterClientDTO request) {
        return null;
    }

    @Override
    public ResponseEntity<?> loginClient(LoginClientDTO request) {
        try {
            String username = request.username();
            String password = request.password();
            Optional<Client> clientDB = clientRepository.findByUsername(username);
            if (clientDB.isPresent()) {
                Client client = clientDB.get();
                if(client.isActive()) {
                    if (password.equals(clientDB.get().getPassword())) {
                        ApiResponse<Client> response = new ApiResponse<>("Success", "Logged in", clientDB.get());
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    } else {
                        ApiResponse<String> response = new ApiResponse<>("Error", "Invalid username or password", null);
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    }
                } else {
                    ApiResponse<String> response = new ApiResponse<>("Error", "Client has not been validated", null);
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                ApiResponse<String> response = new ApiResponse<>("Error", "Account not found in database", null);
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApiResponse<String> response = new ApiResponse<>("Error", "Login logic failed", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
