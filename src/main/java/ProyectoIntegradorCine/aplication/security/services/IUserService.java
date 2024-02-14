package ProyectoIntegradorCine.aplication.security.services;



import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity1;

import ProyectoIntegradorCine.aplication.security.persistence.DTO.UserDto;


import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserEntity1> findAllUsers();

    Optional<UserEntity1> newUser(UserDto userRegistration);
}
