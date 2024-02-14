package ProyectoIntegradorCine.aplication.controller;

import ProyectoIntegradorCine.aplication.services.BookingMovieServices;
import ProyectoIntegradorCine.domain.entity.Booking;
import ProyectoIntegradorCine.domain.models.BookingMovieDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.List;



import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookingMovieControllerTest {
    // Anotacion para realizar las pruebas de integracion
    private MockMvc mockMvc;
    // @Mock para simular mi services
    @Mock
    private BookingMovieServices bookingMovieServices;
    // creamos la configuracion necesaria para la pruebas  en Septup
    @InjectMocks
    private BookingMovieController bookingMovieController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookingMovieController).build();
    }

    @Test
        public void viewMovie() throws Exception {
        // creando objectos que recrean la simulacion de la reserva
            BookingMovieDto booking1 = new BookingMovieDto();
            booking1.setId(1);
            booking1.setName("Movie 1");
            BookingMovieDto booking2 = new BookingMovieDto();
            booking2.setId(2);
            booking2.setName("Movie 2");
            List<BookingMovieDto> expectedBookings = Arrays.asList(booking1, booking2);
//creando el comportamiento esperado
            when(bookingMovieServices.viewReservation()).thenReturn(expectedBookings);
// simulando interacion con el endpoint del controllador
            mockMvc.perform(MockMvcRequestBuilders.get("/allBooking"))
                    .andExpect(status().isOk())
                    .andExpect((ResultMatcher) jsonPath("$[0].id").value(1))
                    .andExpect((ResultMatcher) jsonPath("$[0].name").value("Movie 1"))
                    .andExpect((ResultMatcher) jsonPath("$[1].id").value(2))
                    .andExpect((ResultMatcher) jsonPath("$[1].name").value("Movie 2"));
        }
    }



