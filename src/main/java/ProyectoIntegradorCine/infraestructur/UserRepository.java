package ProyectoIntegradorCine.infraestructur;

import ProyectoIntegradorCine.domain.Entity.UserResgitration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserResgitration, Integer>
{
  Optional<UserResgitration> findByName(String name);


}
