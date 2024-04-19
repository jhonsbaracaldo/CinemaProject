package ProyectoIntegradorCine.aplication.services.servicesSecurity;



import ProyectoIntegradorCine.domain.entity2.UserEntity1;

import ProyectoIntegradorCine.domain.models.dto.UserDto;


import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserEntity1> findAllUsers();

    Optional<UserEntity1> newUser(UserDto userRegistration);
}
