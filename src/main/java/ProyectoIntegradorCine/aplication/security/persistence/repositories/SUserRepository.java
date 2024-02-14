package ProyectoIntegradorCine.aplication.security.persistence.repositories;



import ProyectoIntegradorCine.aplication.security.persistence.entity.UserEntity1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SUserRepository extends JpaRepository<UserEntity1, Long> {


    Optional<UserEntity1> findByEmail(String email);
}
