package ProyectoIntegradorCine.infraestructur.repository;


import ProyectoIntegradorCine.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookingMovieRepository extends JpaRepository<Booking,Integer> {

    Optional<Booking> findProductByName(String name);

}
