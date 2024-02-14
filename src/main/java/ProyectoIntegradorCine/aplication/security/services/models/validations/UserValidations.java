package ProyectoIntegradorCine.aplication.security.services.models.validations;



import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity1;

import ProyectoIntegradorCine.aplication.security.services.models.dtos.ResponseDTO;

public class UserValidations {

    public ResponseDTO validate(UserEntity1 userEntity1){
        ResponseDTO response = new ResponseDTO();

        response.setNumOfErrors(0);

        if (userEntity1.getFirstName() == null || userEntity1.getFirstName().length() < 3 || userEntity1.getFirstName().length() > 15){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo firstName no puede ser nulo, tampoco puede tener menos de 3 caracteres ni mas de 15");
        }

        if (userEntity1.getLastName() == null || userEntity1.getLastName().length() < 3 || userEntity1.getLastName().length() > 30){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo lastName no puede ser nulo, tampoco puede tener menos de 3 caracteres ni mas de 30");
        }

        if (
            userEntity1.getEmail() == null ||
            !userEntity1.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo email no es correcto");
        }

        if(
                userEntity1.getPassword() == null ||
                        !userEntity1.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("La contraseña debe tener entre 8 y 16 caracteres, al menos un número, una minúscula y una mayúscula.");
        }

        return response;
    }
}
