package rs.novacode.spits.spitshl7gw.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import rs.novacode.spits.spitshl7gw.config.SpitsHL7AppConfig;

@SpringBootApplication
@Import({SpitsHL7AppConfig.class})
public class SpitsHl7GwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpitsHl7GwApplication.class, args);
	}

}
