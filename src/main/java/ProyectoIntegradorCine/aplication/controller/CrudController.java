package ProyectoIntegradorCine.aplication.controller;

import ProyectoIntegradorCine.domain.entity.UserRegistration;
import ProyectoIntegradorCine.aplication.services.ServicesUser;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping ("/v1/api")
@RestController
@CrossOrigin(origins = "*")
class CrudController {
    private final ServicesUser servicesUser;

// inyectando en el constructor el services
    @Autowired
    public CrudController(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;}
/// mostrar usuarios
@Operation(summary = "Return view User ")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully users",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserRegistration.class))),
        @ApiResponse(responseCode = "404", description = "No users found",
                content = @Content),
})
@GetMapping(path = "view")
public List<UserRegistration> getUser(){
        return this.servicesUser.getUser();
    }

    @GetMapping(path = "search/{id}")
    public ResponseEntity<Object> getUserId(@PathVariable("id") long id){
        Optional<UserRegistration> userOptional = servicesUser.getUserById(id);
        return userOptional.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

   /// añadir usuario

    @Operation(summary = "modify user list ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "the user was created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRegistration.class))),
            @ApiResponse(responseCode = "404", description = "cannot create a user",
                    content = @Content),
    })
    @PostMapping(path = "add")
    public ResponseEntity<Object> Add(@RequestBody UserRegistration userRegistration){
      return  this.servicesUser.newUser(userRegistration);
    }

    /// actualizando
    @Operation(summary = "update user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "updated user successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRegistration.class))),
            @ApiResponse(responseCode = "409", description = "did not respond to the update",
                    content = @Content),
    })

    @PutMapping(path = "/update/{Id}")
    public ResponseEntity<Object> Update(@PathVariable Long userId, @RequestBody UserRegistration userRegistration){
        return this.servicesUser.updateUser(userId, userRegistration);
    }
    @Operation(summary = "delet user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "delete user successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRegistration.class))),
            @ApiResponse(responseCode = "409", description = "vales monda",
                    content = @Content),
    })
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
        return  this.servicesUser.delete(id);

    }

}