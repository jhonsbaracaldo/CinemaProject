package ProyectoIntegradorCine.aplication.controller;



import ProyectoIntegradorCine.aplication.services.BookingMovieServices;
import ProyectoIntegradorCine.domain.models.BookingMovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/Booking")
public class BookingMovieController {
 private  final BookingMovieServices bookingMovieServices;

    @Autowired
    public BookingMovieController(BookingMovieServices bookingMovieServices) {
        this.bookingMovieServices = bookingMovieServices;
    }
    @GetMapping("/allBooking")
    public List<BookingMovieDto> viewMovie() {
        return bookingMovieServices.viewReservation(); // Ahora retorna DTOs
    }


    @PostMapping("/addBooking")
    public ResponseEntity<Object> selectMovie(@RequestBody BookingMovieDto movieDTO) { // Recibe DTO
        return this.bookingMovieServices.createReservation(movieDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@RequestBody BookingMovieDto movieDTO, @PathVariable Integer id) {
        return bookingMovieServices.updateReservation(movieDTO, id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteBookingMovie(@PathVariable("id") Integer id) {
        return this.bookingMovieServices.delete(id);
    }
}
