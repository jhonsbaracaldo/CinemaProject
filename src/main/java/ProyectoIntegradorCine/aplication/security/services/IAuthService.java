package ProyectoIntegradorCine.aplication.security.services;



import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity1;

import ProyectoIntegradorCine.aplication.security.services.models.dtos.LoginDTO;
import ProyectoIntegradorCine.aplication.security.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception;
    public ResponseDTO register(UserEntity1 userEntity1) throws Exception;
}
