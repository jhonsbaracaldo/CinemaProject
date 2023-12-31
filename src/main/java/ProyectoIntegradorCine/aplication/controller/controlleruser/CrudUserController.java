package ProyectoIntegradorCine.aplication.controller.controlleruser;

import ProyectoIntegradorCine.domain.model.entity.user.UserResgitration;
import ProyectoIntegradorCine.aplication.services.servicesuser.UserService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping ("/v1/api/user")
@RestController
class CrudUserController {
    private final UserService userService;

// inyectando en el constructor el services
    @Autowired
    public CrudUserController(UserService userService) {
        this.userService = userService;}
/// mostrar usuarios
@Operation(summary = "Return view UserResgitration ")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully users",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserResgitration.class))),
        @ApiResponse(responseCode = "404", description = "No users found",
                content = @Content),
})
@GetMapping(path = "view")
public List<UserResgitration> getUser(){
        return this.userService.getUser();
    }
   /// añadir usuario

    @Operation(summary = "modify userResgitration list ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "the userResgitration was created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResgitration.class))),
            @ApiResponse(responseCode = "404", description = "cannot create a userResgitration",
                    content = @Content),
    })
    @PostMapping(path = "add")
    public ResponseEntity<Object> Add(@RequestBody UserResgitration userResgitration){
      return  this.userService.newUser(userResgitration);
    }

    /// actualizando
    @Operation(summary = "update userResgitration ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "updated userResgitration successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResgitration.class))),
            @ApiResponse(responseCode = "409", description = "did not respond to the update",
                    content = @Content),
    })

    @PutMapping(path = "update")
    public ResponseEntity<Object> Update(@RequestBody UserResgitration userResgitration){
        return  this.userService.updateUser(userResgitration);
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
        return  this.userService.delete(id);

    }

}