package pl.swierzewski.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExampleRepository extends MongoRepository<Ticket, String> {
}
