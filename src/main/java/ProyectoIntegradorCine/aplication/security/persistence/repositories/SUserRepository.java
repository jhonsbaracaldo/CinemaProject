package ProyectoIntegradorCine.aplication.security.persistence.repositories;


import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SUserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByEmail(String email);
}
