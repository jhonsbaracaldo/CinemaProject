package ProyectoIntegradorCine.aplication.controller;



import ProyectoIntegradorCine.aplication.services.BookingMovieServices;
import ProyectoIntegradorCine.domain.models.BookingMovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/movie")
public class BookingMovieController {
 private  final BookingMovieServices bookingMovieServices;

    @Autowired
    public BookingMovieController(BookingMovieServices bookingMovieServices) {
        this.bookingMovieServices = bookingMovieServices;
    }
    @GetMapping("/allMovie")
    public List<BookingMovieDto> viewMovie() {
        return bookingMovieServices.viewMovie(); // Ahora retorna DTOs
    }

    @PostMapping("addMovie")
    public ResponseEntity<Object> selectMovie(@RequestBody BookingMovieDto movieDTO) { // Recibe DTO
        return this.bookingMovieServices.selectMovie(movieDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@RequestBody BookingMovieDto movieDTO, @PathVariable Integer id) {
        return bookingMovieServices.updateMovie(movieDTO, id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteBookingMovie(@PathVariable("id") Integer id) {
        return this.bookingMovieServices.delete(id);
    }
}
