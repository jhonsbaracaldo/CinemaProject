package ProyectoIntegradorCine.aplication.security.services.models.validations;


import ProyectoIntegradorCine.aplication.security.persistence.entities.UserEntity;
import ProyectoIntegradorCine.aplication.security.services.models.dtos.ResponseDTO;

public class UserValidations {

    public ResponseDTO validate(UserEntity userEntity){
        ResponseDTO response = new ResponseDTO();

        response.setNumOfErrors(0);

        if (userEntity.getFirstName() == null || userEntity.getFirstName().length() < 3 || userEntity.getFirstName().length() > 15){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo firstName no puede ser nulo, tampoco puede tener menos de 3 caracteres ni mas de 15");
        }

        if (userEntity.getLastName() == null || userEntity.getLastName().length() < 3 || userEntity.getLastName().length() > 30){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo lastName no puede ser nulo, tampoco puede tener menos de 3 caracteres ni mas de 30");
        }

        if (
            userEntity.getEmail() == null ||
            !userEntity.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo email no es correcto");
        }

        if(
                userEntity.getPassword() == null ||
                        !userEntity.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("La contraseña debe tener entre 8 y 16 caracteres, al menos un número, una minúscula y una mayúscula.");
        }

        return response;
    }
}
