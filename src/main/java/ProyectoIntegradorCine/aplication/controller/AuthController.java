package ProyectoIntegradorCine.aplication.controller;



import ProyectoIntegradorCine.domain.entity.UserRegistration;
import ProyectoIntegradorCine.domain.entity2.UserEntity1;

import ProyectoIntegradorCine.aplication.services.servicesSecurity.IAuthService;
import ProyectoIntegradorCine.aplication.services.servicesSecurity.models.dtos.LoginDTO;
import ProyectoIntegradorCine.aplication.services.servicesSecurity.models.dtos.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    private ResponseEntity<ResponseDTO> addUser(@RequestBody UserEntity1 userEntity1) throws Exception {
        return new ResponseEntity<>(authService.register(userEntity1), HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO loginRequest) throws Exception {
        HashMap<String, String> login = authService.login(loginRequest);
        if (login.containsKey("jwt")) {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.UNAUTHORIZED);
        }
    }



}
