package ProyectoIntegradorCine.aplication.security.services;



import ProyectoIntegradorCine.aplication.security.persistence.dto.UserEDto;
import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity1;


import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserEntity1> findAllUsers();

    Optional<UserEntity1> newUser(UserEDto userRegistration);
}
