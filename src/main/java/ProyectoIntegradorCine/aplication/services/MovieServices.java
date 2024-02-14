package ProyectoIntegradorCine.aplication.services;

import ProyectoIntegradorCine.domain.entity.Movie;
import ProyectoIntegradorCine.domain.models.Dto.BookingMovieDto;
import ProyectoIntegradorCine.domain.models.Dto.MovieDto;
import ProyectoIntegradorCine.domain.models.mapper.MapperMovie;
import ProyectoIntegradorCine.infraestructur.repository.IMovieRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServices {

    private final IMovieRepsitory iMovieRepsitory;
    @Autowired
    public MovieServices(IMovieRepsitory iMovieRepsitory) {
        this.iMovieRepsitory = iMovieRepsitory;
    }
    @Autowired
     private MapperMovie mapperMovie;
    HashMap<String, Object> datos = new HashMap<>();

    public List<MovieDto> viewMovie() {
        List<Movie> entities = iMovieRepsitory.findAll();
        return entities.stream().map(movie -> mapperMovie.movieModelMapper().map(movie, MovieDto.class)).toList();
    }

    public Optional<Movie> getMovieById(Long id) {
        return iMovieRepsitory.findById(id);
    }

    public ResponseEntity<Object> createMovie(MovieDto movieDto) {
        Optional<Movie> existingMovie = iMovieRepsitory.findProductByName(movieDto.getName());
        if (existingMovie.isPresent()) {
            datos.put("error", true);
            datos.put("message", "La película ya está reservada");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        } else {
            Movie movie = mapperMovie.movieModelMapper().map(movieDto, Movie.class);
            Movie savedMovie = iMovieRepsitory.save(movie);
            MovieDto savedDTO = mapperMovie.movieModelMapper().map(savedMovie, MovieDto.class); // Regresar DTO
            datos.put("Movie", savedDTO);
            datos.put("", "Create Movie");
            datos.put("movie", movie.getName());
            datos.put("category", movie.getCategory());
            datos.put("fecha de estreno", movie.getDate());
            datos.put("Description", movie.getDescripcion());
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Object> updateMovie(MovieDto movieDto, Integer id) {
        Optional<Movie> existingMovie = iMovieRepsitory.findById(id);
        if (!existingMovie.isPresent()) {
            datos.put("error", true);
            datos.put("message", "La película no existe");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }
        Movie movie = mapperMovie.movieModelMapper().map(movieDto, Movie.class);
        movie.setId(id);
        Movie savedMovie = iMovieRepsitory.save(movie);
        BookingMovieDto savedDTO = mapperMovie.movieModelMapper().map(savedMovie, BookingMovieDto.class);
        datos.put("message", "La película se actualizó exitosamente");
        datos.put("Movie", savedDTO);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    public ResponseEntity<Object> delete(Integer id) {
        boolean existing = this.iMovieRepsitory.existsById(id);
        if (!existing) {
            datos.put("error", true);
            datos.put("massage", "No existe esa pelicula  ");
            return new ResponseEntity<>(datos,
                    HttpStatus.CONFLICT);
        }
        iMovieRepsitory.deleteById(id);
        datos.put("massage", "Booking delete sucesfully  ");
        return new ResponseEntity<>(datos,
                HttpStatus.ACCEPTED);
    }
}
