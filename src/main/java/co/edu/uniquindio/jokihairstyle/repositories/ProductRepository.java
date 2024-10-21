package co.edu.uniquindio.jokihairstyle.repositories;

import co.edu.uniquindio.jokihairstyle.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

