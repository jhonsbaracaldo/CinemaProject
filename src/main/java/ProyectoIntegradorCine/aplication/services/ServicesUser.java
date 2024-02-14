package ProyectoIntegradorCine.aplication.services;



import ProyectoIntegradorCine.infraestructur.repository.UserRepository;
<<<<<<< HEAD
import ProyectoIntegradorCine.domain.entity.UserRegistration;
=======
import ProyectoIntegradorCine.domain.entity.UserResgitration;

import ProyectoIntegradorCine.infraestructur.UserRepository;
import ProyectoIntegradorCine.domain.Entity.UserResgitration;

>>>>>>> 782dca7ddd4d82bf6cb48da0f16b0c783f71fd36
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class    ServicesUser {

    private final UserRepository userRepository;
    HashMap<String, Object> datos = new HashMap<>();
    @Autowired
    public ServicesUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //vista de usuarios@get
    public List<UserRegistration> getUser() {
        return this.userRepository.findAll();
    }

    // vista de usuario por id
    public Optional<UserRegistration> getUserById(Long id) {
        return userRepository.findById(id);
    }
    /// añadir usuarios@post
    public ResponseEntity<Object> newUser(UserRegistration userRegistration) {
        Optional<UserRegistration> res = userRepository.findByName(userRegistration.getName());


        if (res.isPresent()) {
            datos.put("error", true);
            datos.put("massage", "ya hay un usuario registrado con ese nombre ");
            return new ResponseEntity<>(datos,
                    HttpStatus.CONFLICT);
        } else {
            UserRegistration savedUser = userRepository.save(userRegistration);
            datos.put("data", savedUser);
            datos.put("message", "Se guardó con éxito");
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
    }



    /// actualizar@put
    public ResponseEntity<Object> updateUser(Long userId, UserRegistration userRegistration) {
        Map<String, Object> datos = new HashMap<>(); // Crea un nuevo mapa para almacenar los datos de la respuesta

        Optional<UserRegistration> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            UserRegistration existingUser = existingUserOptional.get();
            existingUser.setName(userRegistration.getName());
            existingUser.setEmail(userRegistration.getEmail());

            UserRegistration updatedUser = userRepository.save(existingUser);
            datos.put("message", "Se actualizó con éxito");
            datos.put("data", updatedUser);
            return new ResponseEntity<>(datos, HttpStatus.OK);
        } else {
            datos.put("error", true);
            datos.put("message", "No se encontró ningún usuario con el ID proporcionado");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> delete(Integer id){
    boolean existing= this.userRepository.existsById(id);
    if(!existing){
        datos.put("error", true);
        datos.put("massage", "No existe el usuario  ");
        return new ResponseEntity<>(datos,
                HttpStatus.CONFLICT);
    }
         userRepository.deleteById(id);
        datos.put("massage", "Usuario eliminado   ");
        return new ResponseEntity<>(datos,
                HttpStatus.ACCEPTED);
    }

}
