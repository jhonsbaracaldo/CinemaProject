package ProyectoIntegradorCine.aplication.services.servicesmovies;

import ProyectoIntegradorCine.domain.model.entity.movies.Movie;
import ProyectoIntegradorCine.domain.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServicesTest {

    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServices movieServices;

    private Movie movie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movie = new Movie();
        movie.setName("Fragmentado");
        movie.setCategory("Suspenso");
        movie.setAsientos(5);
        movie.setPrice(13.000);
        movie.setHoraFuncion(LocalDate.parse("2023-02-12"));
        movie.setId(Integer.valueOf(1));

    }

    @Test
    void viewMovie() {
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));
        assertNotNull(movieServices.viewMovie());
    }

    @Test
    void selectMovie() {
        when(movieRepository.findProductByName(movie.getName())).thenReturn(Optional.empty());
        ResponseEntity<Object> response = movieServices.selectMovie(movie);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, Object> datos = (Map<String, Object>) response.getBody();
        assertNotNull(datos);
        assertEquals("Thanks for buy", datos.get(""));
        assertEquals("Fragmentado", datos.get("Movie"));
        assertEquals("Suspenso", datos.get("Category"));
        assertEquals(13.000, datos.get("price"));
        assertEquals(5, datos.get("chairs"));
        assertEquals(65.000, datos.get("total purchase"));
        assertEquals("2023-02-12", datos.get("funtion hour").toString());

    }


    @Test
    void updatetMovie() {
        when(movieRepository.findProductByName(movie.getName())).thenReturn(Optional.of(movie));
        ResponseEntity<Object> response = movieServices.updatetMovie(movie);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, Object> datos = (Map<String, Object>) response.getBody();
        assertNotNull(datos);
        assertEquals(null, datos.get("error"));
        assertEquals("se actualizo la seleccion de la pelicula ", datos.get("message"));
    }

    @Test
    void updateMovieWhenMovieIdIsNotNull() {
        when(movieRepository.findProductByName(movie.getName())).thenReturn(Optional.empty());
        ResponseEntity<Object> response = movieServices.updatetMovie(movie);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, Object> datos = (Map<String, Object>) response.getBody();
        assertNotNull(datos);
        assertEquals("Booking movie Update Sucessfully", datos.get(""));
        String expectedMessage = movie.getId() != null ? "se actualizo la seleccion de la pelicula " : "Se guardó con éxito";
        assertEquals(expectedMessage, datos.get("message"));
    }



    @Test
    void delete() {
        int existingId = 1;
        when(movieRepository.existsById(existingId)).thenReturn(true);
        ResponseEntity<Object> response = movieServices.delete(existingId);
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(movieRepository, times(1)).existsById(existingId);
        verify(movieRepository, times(1)).deleteById(existingId);

    }
}