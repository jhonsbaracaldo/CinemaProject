package ProyectoIntegradorCine.aplication.services.servicesmovies;

import ProyectoIntegradorCine.domain.model.entity.movies.Movie;
import ProyectoIntegradorCine.domain.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServices {
    HashMap<String, Object> datos = new HashMap<>();
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServices(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> viewMovie() {
        return this.movieRepository.findAll();
    }

    public ResponseEntity<Object> selectMovie(Movie movie) {
        Optional<Movie> res = movieRepository.findProductByName(movie.getName());
        if (res.isPresent()) {
            datos.put("error", true);
            datos.put("massage", "ya reservo esta pelicula ");
            return new ResponseEntity<>(datos,
                    HttpStatus.EXPECTATION_FAILED);
        } else {
            Movie savedMovie = movieRepository.save(movie);
            datos.put("", "Thanks for buy");
            datos.put("Movie", movie.getName());
            datos.put("Category", movie.getCategory());
            datos.put("price", movie.getPrice());
            datos.put("chairs", movie.getAsientos());
            datos.put("total purchase", (movie.getAsientos() * movie.getPrice()));
            datos.put("funtion hour", movie.getHoraFuncion());
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Object> updatetMovie(Movie movie) {
        Optional<Movie> res = movieRepository.findProductByName(movie.getName());
        if (res.isPresent() && movie.getId() == null) {
            datos.put("error", true);
            datos.put("message", "I already booked this movie");
            return new ResponseEntity<>(datos,
                    HttpStatus.EXPECTATION_FAILED);
        }
        datos.put("message", "Se guardó con éxito");
        if (movie.getId() !=null) {
            datos.put("message", "se actualizo la seleccion de la pelicula ");
        }
            Movie savedMovie = movieRepository.save(movie);
            datos.put("", "Booking movie Update Sucessfully");
            datos.put("id",movie.getId());
            datos.put("Movie", movie.getName());
            datos.put("Category", movie.getCategory());
            datos.put("price", movie.getPrice());
            datos.put("chairs", movie.getAsientos());
            datos.put("total purchase", (movie.getAsientos() * movie.getPrice()));
            datos.put("funtion hour", movie.getHoraFuncion());
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }

        public ResponseEntity<Object> delete(Integer id){
            boolean existing= this.movieRepository.existsById(id);
            if(!existing){
                datos.put("error", true);
                datos.put("massage", "No existe esta funcion  ");
                return new ResponseEntity<>(datos,
                        HttpStatus.CONFLICT);
            }
            movieRepository.deleteById(id);
            datos.put("massage", "Booking delete sucesfully  ");
            return new ResponseEntity<>(datos,
                    HttpStatus.ACCEPTED);
        }

    }




