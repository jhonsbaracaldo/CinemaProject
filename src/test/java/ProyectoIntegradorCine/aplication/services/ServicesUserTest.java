package ProyectoIntegradorCine.aplication.services;

import ProyectoIntegradorCine.domain.entity.UserResgitration;
import ProyectoIntegradorCine.infraestructur.repository.UserRepository;
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
import static org.mockito.Mockito.*;
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
        List<UserResgitration> userList = new ArrayList<>();
        userList.add(new UserResgitration("Juan", "Rodri", 1, "juan@example.com"));
        userList.add(new UserResgitration("Alice", "paez", 2, "alice@example.com"));

        when(userRepository.findAll()).thenReturn(userList);

        List<UserResgitration> result = servicesUser.getUser();

        assertEquals(2, result.size());
    }

    @Test
    void newUser() {
        UserResgitration newUser = new UserResgitration("harold", "bronco", 3, "harold@example.com");
        when(userRepository.findByName("harold")).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(newUser);
        ResponseEntity<Object> response = servicesUser.newUser(newUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void newUserWithNameAlreadyExists() {
        UserResgitration existingUser = new UserResgitration("Alice", "paez", 2, "alice@example.com");
        when(userRepository.findByName("Alice")).thenReturn(Optional.of(existingUser));
        ResponseEntity<Object> response = servicesUser.newUser(existingUser);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

     @Test
    void updateUser() {
         UserResgitration userToUpdate = new UserResgitration("Alice", "paez", 2, "alice@example.com");
         when(userRepository.findByName("John")).thenReturn(Optional.of(userToUpdate));
        when(userRepository.save(userToUpdate)).thenReturn(userToUpdate);

        ResponseEntity<Object> response = servicesUser.updateUser(userToUpdate);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateUserWithExistingName() {
        UserResgitration existingUser = new UserResgitration("Alice", "paez", 2, "alice@example.com");
        when(userRepository.findByName("Alice")).thenReturn(Optional.of(existingUser));
        ResponseEntity<Object> response = servicesUser.updateUser(existingUser);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void updateUserWithExistingNameAndId() {
        UserResgitration existingUser = new UserResgitration("Alice", "paez", 2, "alice@example.com");
        existingUser.setId(1);
        when(userRepository.findByName("Alice")).thenReturn(Optional.of(existingUser));
        ResponseEntity<Object> response = servicesUser.updateUser(existingUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
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
