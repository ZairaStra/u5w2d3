package zairastra.u5w2d3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zairastra.u5w2d3.entities.Blog;

@Repository
public interface BlogsRepository extends JpaRepository<Blog, Integer> {
}
