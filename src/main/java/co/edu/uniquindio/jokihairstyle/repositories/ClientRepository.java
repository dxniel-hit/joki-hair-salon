package co.edu.uniquindio.jokihairstyle.repositories;

import co.edu.uniquindio.jokihairstyle.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}
