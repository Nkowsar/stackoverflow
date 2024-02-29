package stackOverFlow.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackOverFlow.security.entities.Answers;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {
    List<Answers> findAllByQuestionId(long questionId);
}
