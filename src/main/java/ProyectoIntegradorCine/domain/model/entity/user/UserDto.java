package ProyectoIntegradorCine.domain.model.entity.user;

import ProyectoIntegradorCine.Security.ERole;

public record UserDto(
        Integer id,
        String Name,
        String lastName,
        String email,
        String password,
        Boolean enable,
        ERole role

) {

}
