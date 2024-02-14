package ProyectoIntegradorCine.aplication.services;

import ProyectoIntegradorCine.domain.entity.Booking;
import ProyectoIntegradorCine.domain.models.BookingMovieDto;
import ProyectoIntegradorCine.infraestructur.repository.IBookingMovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingMovieServicesTest {

    @Mock
    private IBookingMovieRepository movieRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookingMovieServices bookingMovieServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void viewMovie() {
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking(1, "Avengers", "Sala 1", 50, LocalDate.now()));
        bookingList.add(new Booking(2, "Spiderman", "Sala 2", 40, LocalDate.now()));

        when(movieRepository.findAll()).thenReturn(bookingList);

        List<BookingMovieDto> result = bookingMovieServices.viewReservation();

        assertEquals(2, result.size());
    }

    @Test
    void selectMovie() {
        BookingMovieDto bookingDto = new BookingMovieDto("Joker", "Sala 3", 30, LocalDate.now());
        when(movieRepository.findProductByName("Joker")).thenReturn(Optional.empty());
        when(modelMapper.map(bookingDto, Booking.class)).thenReturn(new Booking(1, "Joker", "Sala 3", 30, LocalDate.now()));
        when(modelMapper.map(any(Booking.class), eq(BookingMovieDto.class))).thenReturn(bookingDto);
        when(movieRepository.save(any(Booking.class))).thenReturn(new Booking(1, "Joker", "Sala 3", 30, LocalDate.now()));

        ResponseEntity<Object> response = bookingMovieServices.createReservation(bookingDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void selectMovieWithExistingBooking() {
        BookingMovieDto bookingDto = new BookingMovieDto("Avengers", "Sala 1", 50, LocalDate.now());
        when(movieRepository.findProductByName("Avengers")).thenReturn(Optional.of(new Booking()));

        ResponseEntity<Object> response = bookingMovieServices.createReservation(bookingDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateMovie() {
        BookingMovieDto bookingDto = new BookingMovieDto("Joker", "Sala 3", 30, LocalDate.now());
        int id = 1;
        when(movieRepository.findById(id)).thenReturn(Optional.of(new Booking()));
        when(modelMapper.map(bookingDto, Booking.class)).thenReturn(new Booking(id, "Joker", "Sala 3", 30, LocalDate.now()));
        when(movieRepository.save(any(Booking.class))).thenReturn(new Booking(id, "Joker", "Sala 3", 30, LocalDate.now()));

        ResponseEntity<Object> response = bookingMovieServices.updateReservation(bookingDto, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateNonExistingMovie() {
        BookingMovieDto bookingDto = new BookingMovieDto("Joker", "Sala 3", 30, LocalDate.now());
        int id = 1;
        when(movieRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = bookingMovieServices.updateReservation(bookingDto, id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteMovie() {
        int id = 1;
        when(movieRepository.existsById(id)).thenReturn(true);

        ResponseEntity<Object> response = bookingMovieServices.delete(id);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void deleteNonExistingMovie() {
        int id = 1;
        when(movieRepository.existsById(id)).thenReturn(false);

        ResponseEntity<Object> response = bookingMovieServices.delete(id);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
