package ProyectoIntegradorCine.aplication.services.servicesSecurity.impl;




import ProyectoIntegradorCine.domain.entity2.UserEntity1;

import ProyectoIntegradorCine.domain.models.dto.UserDto;

import ProyectoIntegradorCine.infraestructure.repositories.SUserRepository;
import ProyectoIntegradorCine.aplication.services.servicesSecurity.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    SUserRepository SUserRepository;


    @Override
    public List<UserEntity1> findAllUsers(){
        return SUserRepository.findAll();
    }


    @Override
    public Optional<UserEntity1> newUser(UserDto userdto) {
         UserEntity1 userEntity1 = new UserEntity1();
         userEntity1.setFirstName(userdto.getFirstname());
         userEntity1.setLastName(userdto.getLastname());
         userEntity1.setPhone(userdto.getPhone());
         userEntity1.setEmail(userdto.getEmail());
         userEntity1.setPassword(userdto.getPassword());
         return Optional.of(SUserRepository.save(userEntity1));
    }
}
