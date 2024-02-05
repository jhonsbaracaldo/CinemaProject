package ProyectoIntegradorCine.aplication.controller;

import ProyectoIntegradorCine.domain.Entity.UserResgitration;
import ProyectoIntegradorCine.aplication.services.ServicesUser;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping ("/v1/api")
@RestController
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
                        schema = @Schema(implementation = UserResgitration.class))),
        @ApiResponse(responseCode = "404", description = "No users found",
                content = @Content),
})
@GetMapping(path = "view")
public List<UserResgitration> getUser(){
        return this.servicesUser.getUser();
    }
   /// añadir usuario

    @Operation(summary = "modify user list ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "the user was created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResgitration.class))),
            @ApiResponse(responseCode = "404", description = "cannot create a user",
                    content = @Content),
    })
    @PostMapping(path = "add")
    public ResponseEntity<Object> Add(@RequestBody UserResgitration userResgitration){
      return  this.servicesUser.newUser(userResgitration);
    }

    /// actualizando
    @Operation(summary = "update user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "updated user successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResgitration.class))),
            @ApiResponse(responseCode = "409", description = "did not respond to the update",
                    content = @Content),
    })

    @PutMapping(path = "update")
    public ResponseEntity<Object> Update(@RequestBody UserResgitration userResgitration){
        return  this.servicesUser.updateUser(userResgitration);
    }
    @Operation(summary = "delet user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "delete user successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResgitration.class))),
            @ApiResponse(responseCode = "409", description = "vales monda",
                    content = @Content),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
        return  this.servicesUser.delete(id);

    }

}