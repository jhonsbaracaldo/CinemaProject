package ProyectoIntegradorCine.aplication.controller;




import ProyectoIntegradorCine.domain.entity2.UserEntity1;

import ProyectoIntegradorCine.domain.models.dto.UserDto;

import ProyectoIntegradorCine.aplication.services.servicesSecurity.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/all")
    private ResponseEntity<List<UserEntity1>> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userRegistration) {
        Optional<UserEntity1> newUser = userService.newUser(userRegistration);

        if (newUser.isPresent()) {
            return new ResponseEntity<>(newUser.get(), HttpStatus.CREATED);
        } else {
            // Puedes manejar el caso en el que no se pudo crear el usuario
            return new ResponseEntity<>("No se pudo crear el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
