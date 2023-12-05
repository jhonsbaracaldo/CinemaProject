package ProyectoIntegradorCine;

import ProyectoIntegradorCine.Controller.Entity.UserResgitration;
import ProyectoIntegradorCine.Controller.Services.ServicesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping ("/v1/api")
@RestController
class CrudController {
    private final ServicesUser servicesUser;
// inyectando en el constructor el services
    @Autowired
    public CrudController(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;}
/// mostrar usuarios
    @GetMapping(path = "correr")
    public List<UserResgitration> getUser(){
        return this.servicesUser.getUser();
    }
   /// añadir usuario
    @PostMapping(path = "add")
    public ResponseEntity<Object> Add(@RequestBody UserResgitration userResgitration){
      return  this.servicesUser.newUser(userResgitration);
    }

    /// actualizando
    @PutMapping(path = "update")
    public ResponseEntity<Object> Update(@RequestBody UserResgitration userResgitration){
        return  this.servicesUser.updateUser(userResgitration);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
        return  this.servicesUser.delete(id);

    }

}