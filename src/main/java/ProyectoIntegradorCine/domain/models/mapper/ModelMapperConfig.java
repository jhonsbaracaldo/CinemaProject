package ProyectoIntegradorCine.domain.models.mapper;


import ProyectoIntegradorCine.domain.entity.Booking;
import ProyectoIntegradorCine.domain.models.BookingMovieDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Booking.class, BookingMovieDto.class);
        return modelMapper;
    }
}