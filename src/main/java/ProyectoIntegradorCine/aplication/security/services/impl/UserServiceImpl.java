package ProyectoIntegradorCine.aplication.security.services.impl;


import ProyectoIntegradorCine.aplication.security.persistence.dto.UserDto;
import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity;
import ProyectoIntegradorCine.aplication.security.persistence.repositories.SUserRepository;
import ProyectoIntegradorCine.aplication.security.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    SUserRepository SUserRepository;


    @Override
    public List<UserEntity> findAllUsers(){

        return SUserRepository.findAll();
    }


    @Override
    public Optional<UserEntity> newUser(UserDto userdto) {
         UserEntity userEntity = new UserEntity();
         userEntity.setFirstName(userdto.getFirstname());
         userEntity.setLastName(userdto.getLastname());
         userEntity.setPhone(userdto.getPhone());
         userEntity.setEmail(userdto.getEmail());
         userEntity.setPassword(userdto.getPassword());
         return Optional.of(SUserRepository.save(userEntity));
    }
}
