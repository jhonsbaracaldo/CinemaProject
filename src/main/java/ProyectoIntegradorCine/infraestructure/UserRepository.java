package ProyectoIntegradorCine.infraestructure;

import ProyectoIntegradorCine.domain.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration, Integer>
{
  Optional<UserRegistration> findByName(String name);
    Optional<UserRegistration> findById(Long id);

}
