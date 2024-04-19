package ProyectoIntegradorCine.infraestructure;

import ProyectoIntegradorCine.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovieRepsitory extends JpaRepository<Movie, Integer> {
    Optional<Movie> findProductByName(String name);


    Optional<Movie> findById(Long id);
}
