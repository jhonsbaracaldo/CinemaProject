package ProyectoIntegradorCine.aplication.controller;

import ProyectoIntegradorCine.aplication.services.serviceJwt.AuthenticationServices;
import ProyectoIntegradorCine.aplication.services.servicesuser.AuthenticationDto;
import ProyectoIntegradorCine.domain.model.entity.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserDefinedFileAttributeView;

@RequestMapping
@RestController("/api/v1/auth")
public record AutheticatorController(
    AuthenticationServices authenticationServices
) {

    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody UserDto userDto){
        String token = authenticationServices.register(userDto);
        return  new ResponseEntity<>(token, HttpStatus.CREATED);

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authentication(@RequestBody AuthenticationDto authenticationDto){
        String token =authenticationServices.athenticate(authenticationDto);
      return  new ResponseEntity<>(token, HttpStatus.OK);
  }
}
