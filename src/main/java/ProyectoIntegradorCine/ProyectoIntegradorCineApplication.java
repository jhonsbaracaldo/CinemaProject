package ProyectoIntegradorCine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@SpringBootApplication
@RestController
public class ProyectoIntegradorCineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoIntegradorCineApplication.class, args);
	}



}
