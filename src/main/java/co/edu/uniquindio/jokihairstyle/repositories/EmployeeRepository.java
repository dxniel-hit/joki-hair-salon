package co.edu.uniquindio.jokihairstyle.repositories;

import co.edu.uniquindio.jokihairstyle.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
