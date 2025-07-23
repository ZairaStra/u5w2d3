package zairastra.u5w2d3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zairastra.u5w2d3.entities.Author;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);
}
