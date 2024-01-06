package ProyectoIntegradorCine.aplication.services.servicesuser;

import ProyectoIntegradorCine.domain.model.entity.user.UserResgitration;
import ProyectoIntegradorCine.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @InjectMocks
    private  UserService userService;
    @Mock
    private UserRepository userRepository;
    private UserResgitration userResgitration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userResgitration = new UserResgitration();
        userResgitration.setIdUser(1);
        userResgitration.setName("jhon");
        userResgitration.setLastName("baracaldo");
        userResgitration.setEmail("jhonsbaracaldo@ada.com");

    }

    @Test
    void getUser() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(userResgitration));
        assertNotNull(userService.getUser());
    }

    @Test
    void newUser() {
        when(userRepository.findByName(userResgitration.getName())).thenReturn(Optional.empty());
        ResponseEntity<Object> response = userService.newUser(userResgitration);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Map<String, Object> datos = (Map<String, Object>) response.getBody();
        assertNotNull(datos);
        assertEquals("Se guardó con éxito", datos.get("message"));
    }
    /// corregir
    @Test
    void updateUser() {
        when(userRepository.findByName(userResgitration.getName())).thenReturn(Optional.of(userResgitration));
        userResgitration.setIdUser(1);
        ResponseEntity<Object> response = userService.updateUser(userResgitration);
        Map<String, Object> datos = (Map<String, Object>) response.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(null, datos.get("message"));
        verify(userRepository, never()).save(any(UserResgitration.class));
    }

    @Test
    void delete() {
        int existingId = 1;
        when(userRepository.existsById(existingId)).thenReturn(true);
        ResponseEntity<Object> response =userService.delete(existingId);
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(userRepository, times(1)).existsById(existingId);
        verify(userRepository, times(1)).deleteById(existingId);

    }
}