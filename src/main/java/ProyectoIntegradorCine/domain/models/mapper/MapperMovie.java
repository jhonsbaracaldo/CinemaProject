package ProyectoIntegradorCine.domain.models.mapper;


import ProyectoIntegradorCine.domain.entity.Movie;
import ProyectoIntegradorCine.domain.models.BookingMovieDto;
import ProyectoIntegradorCine.domain.models.MovieDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperMovie {
        @Bean
        public ModelMapper movieModelMapper() {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.createTypeMap(Movie.class, MovieDto.class);
            return modelMapper;
        }
    }

