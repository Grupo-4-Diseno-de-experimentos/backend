package pe.edu.upc.trabajo.Trabajo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TrabajoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabajoApplication.class, args);
	}

}
