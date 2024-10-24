package co.edu.uniquindio.jokihairstyle.repositories;

import co.edu.uniquindio.jokihairstyle.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client> findByUsername(String username);
}
