package ProyectoIntegradorCine.aplication.services;

import ProyectoIntegradorCine.aplication.services.serviceEntity.ServicesUser;
import ProyectoIntegradorCine.domain.entity.UserRegistration;
import ProyectoIntegradorCine.infraestructure.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ServicesUserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ServicesUser servicesUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUser() {
        List<UserRegistration> userList = new ArrayList<>();
        userList.add(new UserRegistration("Juan", "Rodri", 1, "juan@example.com"));
        userList.add(new UserRegistration("Alice", "paez", 2, "alice@example.com"));

        when(userRepository.findAll()).thenReturn(userList);

        List<UserRegistration> result = servicesUser.getUser();

        assertEquals(2, result.size());
    }

    @Test
    void newUser() {
        UserRegistration newUser = new UserRegistration("harold", "bronco", 3, "harold@example.com");
        when(userRepository.findByName("harold")).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(newUser);
        ResponseEntity<Object> response = servicesUser.newUser(newUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void newUserWithNameAlreadyExists() {
        UserRegistration existingUser = new UserRegistration("Alice", "paez", 2, "alice@example.com");
        when(userRepository.findByName("Alice")).thenReturn(Optional.of(existingUser));
        ResponseEntity<Object> response = servicesUser.newUser(existingUser);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }





    @Test
    void deleteUser() {
        int userId = 1;
        when(userRepository.existsById(userId)).thenReturn(true);
        ResponseEntity<Object> response = servicesUser.delete(userId);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void deleteUserWithNonExistingId() {
        int userId = 1;
        when(userRepository.existsById(userId)).thenReturn(false);
        ResponseEntity<Object> response = servicesUser.delete(userId);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}

