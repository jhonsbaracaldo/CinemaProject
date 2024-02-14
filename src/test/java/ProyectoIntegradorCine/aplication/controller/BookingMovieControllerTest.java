package ProyectoIntegradorCine.aplication.controller;

import ProyectoIntegradorCine.aplication.services.BookingMovieServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

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
    void viewMovie() {
    }
}