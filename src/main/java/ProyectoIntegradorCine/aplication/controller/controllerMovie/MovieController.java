package ProyectoIntegradorCine.aplication.controller.controllerMovie;


import ProyectoIntegradorCine.aplication.services.servicesmovies.MovieServices;
import ProyectoIntegradorCine.domain.model.entity.movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/movie")
public class MovieController {
 private  final MovieServices movieServices;

    @Autowired
    public MovieController(MovieServices movieServices) {
        this.movieServices = movieServices;
    }
@GetMapping
    public List<Movie> viewMovie(){
      return movieServices.viewMovie();
    }

    @PostMapping
    public ResponseEntity<Object> selectMovie(@RequestBody Movie movie) {
        return this.movieServices.selectMovie(movie);
    }

    @PutMapping
    public ResponseEntity<Object> updateMovie(@RequestBody Movie movie) {
        return this.movieServices.updatetMovie(movie);
    }

    @DeleteMapping (path = ("{id"))
     public ResponseEntity<Object> deletBookingMovie(@PathVariable("id") Integer id){
        return this.movieServices.delete(id);
    }
}
