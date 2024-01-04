package pl.swierzewski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.swierzewski.infrastructure.ExampleRepository;
import pl.swierzewski.infrastructure.Ticket;

@SpringBootApplication
public class LottoSpringBootApplication implements CommandLineRunner {

    @Autowired
    ExampleRepository exampleRepository;

    public static void main(String[] args) {
        SpringApplication.run(LottoSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        exampleRepository.save(new Ticket("example data"));
    }
}