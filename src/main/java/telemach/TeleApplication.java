package telemach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class TeleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeleApplication.class, args);
	}
}
