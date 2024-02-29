package stackOverFlow.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackOverFlow.security.entities.Answers;
import stackOverFlow.security.entities.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findByAnswers(Answers answers);
}
