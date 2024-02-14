package ProyectoIntegradorCine.infraestructur.repository;

import ProyectoIntegradorCine.domain.entity.Booking;
import ProyectoIntegradorCine.domain.entity.Movie;
import ProyectoIntegradorCine.domain.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovieRepsitory extends JpaRepository<Movie, Integer> {
    Optional<Movie> findProductByName(String name);


    Optional<Movie> findById(Long id);
}
