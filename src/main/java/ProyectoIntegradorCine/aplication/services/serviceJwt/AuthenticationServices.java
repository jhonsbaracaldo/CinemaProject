package ProyectoIntegradorCine.aplication.services.serviceJwt;

import ProyectoIntegradorCine.Security.ERole;
import ProyectoIntegradorCine.aplication.services.servicesuser.AuthenticationDto;
import ProyectoIntegradorCine.domain.model.entity.user.UserDto;
import ProyectoIntegradorCine.domain.model.entity.user.UserResgitration;
import ProyectoIntegradorCine.domain.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record AuthenticationServices(UserRepository userRepository,
                                     JwtServices jwtServices,
                                     PasswordEncoder passwordEncoder,

                                     AuthenticationManager authenticationManager
) {



    public String register(UserDto userDto){
        UserResgitration user = UserResgitration.builder()
                .name(userDto.Name())
                .lastName(userDto.lastName())
                .email(userDto.email())
                .password(passwordEncoder.encode(userDto.password()))
                .erole(ERole.USER)
                .enable(true)
                .build();
        userRepository.save(user);
        jwtServices.generateToken(user);
        return jwtServices.generateToken(user);
    }


    public String athenticate(AuthenticationDto authenticationDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.email(),
                        authenticationDto.password()
                )
        );

        UserResgitration user = userRepository.findUserByEmail(authenticationDto.email())
                .orElseThrow();
        return jwtServices.generateToken(user);
    }




}