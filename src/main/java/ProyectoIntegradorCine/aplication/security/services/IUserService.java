package ProyectoIntegradorCine.aplication.security.services;

import ProyectoIntegradorCine.aplication.security.persistence.dto.UserDto;
import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserEntity> findAllUsers();

    Optional<UserEntity> newUser(UserDto userRegistration);
}
