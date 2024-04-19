package ProyectoIntegradorCine.aplication.services.servicesSecurity;



import ProyectoIntegradorCine.domain.entity2.UserEntity1;

import ProyectoIntegradorCine.aplication.services.servicesSecurity.models.dtos.LoginDTO;
import ProyectoIntegradorCine.aplication.services.servicesSecurity.models.dtos.ResponseDTO;

import java.util.HashMap;
import java.util.List;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception;
    public ResponseDTO register(UserEntity1 userEntity1) throws Exception;

    public List<UserEntity1> viewAll() throws Exception;
}
