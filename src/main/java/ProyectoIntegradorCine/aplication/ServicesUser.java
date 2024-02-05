package ProyectoIntegradorCine.aplication;


import ProyectoIntegradorCine.infraestructur.UserRepository;
import ProyectoIntegradorCine.domain.Entity.UserResgitration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    public List<UserResgitration> getUser() {
        return this.userRepository.findAll();

    }

    /// añadir usuarios@post
    public ResponseEntity<Object> newUser(UserResgitration userResgitration) {
        Optional<UserResgitration> res = userRepository.findByName(userResgitration.getName());


        if (res.isPresent()) {
            datos.put("error", true);
            datos.put("massage", "ya hay un usuario registrado con ese nombre ");
            return new ResponseEntity<>(datos,
                    HttpStatus.CONFLICT);
        } else {
            UserResgitration savedUser = userRepository.save(userResgitration);
            datos.put("data", savedUser);
            datos.put("message", "Se guardó con éxito");
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        }
    }

    /// actualizar@put
    public ResponseEntity<Object> updateUser(UserResgitration userResgitration) {
        Optional<UserResgitration> res = userRepository.findByName(userResgitration.getName());


        if (res.isPresent()&& userResgitration.getId()==null) {
            datos.put("error", true);
            datos.put("massage", "ya hay un usuario registrado con ese nombre ");
            return new ResponseEntity<>(datos,
                    HttpStatus.CONFLICT);
        }
        datos.put("message", "Se guardó con éxito");
        if (userResgitration.getId() != null) {
            datos.put("message", "Se actualizo con exito ");
        }
            UserResgitration savedUser = userRepository.save(userResgitration);
            datos.put("data", savedUser);

            return new ResponseEntity<>(datos, HttpStatus.CREATED);

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
