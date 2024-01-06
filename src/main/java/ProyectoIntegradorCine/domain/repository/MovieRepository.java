package ProyectoIntegradorCine.domain.repository;


import ProyectoIntegradorCine.domain.model.entity.movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Optional<Movie> findProductByName(String name);

public interface MovieRepository {


}
