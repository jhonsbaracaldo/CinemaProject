package ProyectoIntegradorCine.aplication.security.services;



import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity;

import ProyectoIntegradorCine.aplication.security.persistence.entities.UserEntity;

import ProyectoIntegradorCine.aplication.security.services.models.dtos.LoginDTO;
import ProyectoIntegradorCine.aplication.security.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception;
    public ResponseDTO register(UserEntity userEntity) throws Exception;
}
