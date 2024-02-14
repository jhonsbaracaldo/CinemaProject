package ProyectoIntegradorCine.aplication.security.services.impl;



import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity1;

import ProyectoIntegradorCine.infraestructur.repository.SUserRepository;
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
            Optional<UserEntity1> user = SUserRepository.findByEmail(loginRequest.getEmail());

            if (user.isEmpty()) {
                jwt.put("error", "UserEntity1 not registered!");
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
    public ResponseDTO register(UserEntity1 userEntity1) throws Exception {
        try {
            ResponseDTO response = userValidations.validate(userEntity1);
            List<UserEntity1> getAllUserEntities = SUserRepository.findAll();

            if (response.getNumOfErrors() > 0){
                return response;
            }


            // Verificación de correo electrónico existente
            List<UserEntity1> allUserEntities = SUserRepository.findAll();
            for (UserEntity1 existingUserEntity1 : getAllUserEntities) {
                if (existingUserEntity1.getEmail().equals(userEntity1.getEmail())) {
                    response.setMessage("UserEntity1 with the same email already exists!");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            userEntity1.setPassword(encoder.encode(userEntity1.getPassword()));
            SUserRepository.save(userEntity1);
            response.setMessage("UserEntity1 created successfully!");
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
