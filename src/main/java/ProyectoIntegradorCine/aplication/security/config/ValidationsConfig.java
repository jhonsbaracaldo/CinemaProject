package ProyectoIntegradorCine.aplication.security.config;


import ProyectoIntegradorCine.aplication.security.services.models.validations.UserValidations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationsConfig {

    @Bean
    public UserValidations userValidations(){
        return new UserValidations();
    }
}
