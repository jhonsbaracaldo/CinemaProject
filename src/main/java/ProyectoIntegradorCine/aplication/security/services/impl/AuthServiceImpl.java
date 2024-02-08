package ProyectoIntegradorCine.aplication.security.services.impl;


import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity;
import ProyectoIntegradorCine.aplication.security.persistence.repositories.SUserRepository;
import ProyectoIntegradorCine.aplication.security.services.IAuthService;
import ProyectoIntegradorCine.aplication.security.services.IJWTUtilityService;
import ProyectoIntegradorCine.aplication.security.services.models.dtos.LoginDTO;
import ProyectoIntegradorCine.aplication.security.services.models.dtos.ResponseDTO;
import ProyectoIntegradorCine.aplication.security.services.models.validations.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private SUserRepository SUserRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserValidations userValidations;

    @Override
    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = SUserRepository.findByEmail(loginRequest.getEmail());

            if (user.isEmpty()) {
                jwt.put("error", "UserEntity not registered!");
                return jwt;
            }
            if (verifyPassword(loginRequest.getPassword(), user.get().getPassword())) {
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
            } else {
                jwt.put("error", "Authentication failed");
            }
            return jwt;
        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }

    @Override
    public ResponseDTO register(UserEntity userEntity) throws Exception {
        try {
            ResponseDTO response = userValidations.validate(userEntity);
            List<UserEntity> getAllUserEntities = SUserRepository.findAll();

            if (response.getNumOfErrors() > 0){
                return response;
            }


            // Verificación de correo electrónico existente
            List<UserEntity> allUserEntities = SUserRepository.findAll();
            for (UserEntity existingUserEntity : getAllUserEntities) {
                if (existingUserEntity.getEmail().equals(userEntity.getEmail())) {
                    response.setMessage("UserEntity with the same email already exists!");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            userEntity.setPassword(encoder.encode(userEntity.getPassword()));
            SUserRepository.save(userEntity);
            response.setMessage("UserEntity created successfully!");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
