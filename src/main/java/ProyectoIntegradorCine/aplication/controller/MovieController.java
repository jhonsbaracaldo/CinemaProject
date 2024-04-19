package ProyectoIntegradorCine.aplication.controller;

import ProyectoIntegradorCine.aplication.services.serviceEntity.MovieServices;
import ProyectoIntegradorCine.domain.entity.Movie;
import ProyectoIntegradorCine.domain.models.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/v1/Movie")
@RestController
public class MovieController {

    private  final MovieServices movieServices;
    @Autowired
    public MovieController(MovieServices movieServices) {
        this.movieServices = movieServices;
    }

    @GetMapping("allMovie")
    public List<MovieDto> viewMovie() {
        return movieServices.viewMovie(); // Ahora retorna DTOs
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable("id") Long id) {
        Optional<Movie> movieDtoOptional = movieServices.getMovieById(id);
        return movieDtoOptional.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping("addMovie")
    public ResponseEntity<Object> selectMovie(@RequestBody MovieDto movieDTO) { // Recibe DTO
        return this.movieServices.createMovie(movieDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@RequestBody MovieDto movieDTO, @PathVariable Integer id) {
        return movieServices.updateMovie(movieDTO, id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteBookingMovie(@PathVariable("id") Integer id) {
        return this.movieServices.delete(id);
    }
}
