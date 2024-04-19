package ProyectoIntegradorCine.aplication.controller;

import ProyectoIntegradorCine.aplication.services.serviceEntity.BookingMovieServices;
import ProyectoIntegradorCine.domain.models.BookingMovieDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookingMovieControllerTest {
    // Anotacion para realizar las pruebas de integracion
    private MockMvc mockMvc;
    // @Mock para simular mi services
    @Mock
    private BookingMovieServices bookingMovieServices;
    // creamos la configuracion necesaria para la pruebas  en Setup
    @InjectMocks
    private BookingMovieController bookingMovieController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializar los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(bookingMovieController).build();
    }

    @Test
    public void viewMovie() throws Exception {
        // Preparar datos de prueba
        BookingMovieDto booking1 = new BookingMovieDto();
        booking1.setId(1);
        booking1.setName("Movie 1");
        BookingMovieDto booking2 = new BookingMovieDto();
        booking2.setId(2);
        booking2.setName("Movie 2");
        List<BookingMovieDto> expectedBookings = Arrays.asList(booking1, booking2);

        // Configurar comportamiento del servicio mock
        when(bookingMovieServices.viewReservation()).thenReturn(expectedBookings);

        // Ejecutar la prueba y verificar el resultado
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/Booking/allBooking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Movie 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Movie 2"));
    }

}
