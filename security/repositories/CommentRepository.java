package stackOverFlow.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackOverFlow.security.entities.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
