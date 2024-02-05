package ProyectoIntegradorCine.aplication.services;




import ProyectoIntegradorCine.domain.Entity.Booking;
import ProyectoIntegradorCine.domain.models.BookingMovieDto;
import ProyectoIntegradorCine.infraestructur.IBookingMovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BookingMovieServices {
    HashMap<String, Object> datos = new HashMap<>();
    @Autowired
    private IBookingMovieRepository movieRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<BookingMovieDto> viewMovie() {
        List<Booking> entities = movieRepository.findAll();
        return entities.stream().map(booking -> modelMapper.map(booking, BookingMovieDto.class)).toList();
    }

    public ResponseEntity<Object> selectMovie(BookingMovieDto bookingmovieDTO) {
        Optional<Booking> existingBooking = movieRepository.findProductByName(bookingmovieDTO.getName());
        if (existingBooking.isPresent()) {
            datos.put("error", true);
            datos.put("message", "La película ya está reservada");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        } else {
            Booking booking = modelMapper.map(bookingmovieDTO, Booking.class);
            Booking savedBooking = movieRepository.save(booking);
            BookingMovieDto savedDTO = modelMapper.map(savedBooking, BookingMovieDto.class); // Regresar DTO
            datos.put("Booking", savedDTO);
            datos.put("", "Thanks for buy");
            datos.put("Booking", booking.getName());
            datos.put("funtion hour", booking.getHoraFuncion());
            datos.put("sala", booking.getAsientos());
            datos.put("chairs", booking.getAsientos());
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Object> updateMovie(BookingMovieDto bookingmovieDTO, Integer id) {
        Optional<Booking> existingBooking = movieRepository.findById(id);
        if (!existingBooking.isPresent()) {
            datos.put("error", true);
            datos.put("message", "La película no existe");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }
        Booking booking = modelMapper.map(bookingmovieDTO, Booking.class);
        booking.setId(id);
        Booking savedBooking = movieRepository.save(booking);
        BookingMovieDto savedDTO = modelMapper.map(savedBooking, BookingMovieDto.class);
        datos.put("message", "La película se actualizó exitosamente");
        datos.put("Booking", savedDTO);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    public ResponseEntity<Object> delete(Integer id) {
        boolean existing = this.movieRepository.existsById(id);
        if (!existing) {
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







