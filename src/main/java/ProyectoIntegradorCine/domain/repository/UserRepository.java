package ProyectoIntegradorCine.domain.repository;

import ProyectoIntegradorCine.domain.model.entity.user.UserResgitration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserResgitration, Integer>
{
  Optional<UserResgitration> findByName(String name);
  Optional<UserResgitration> findUserByEmail(String email);

}
