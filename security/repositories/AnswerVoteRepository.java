package stackOverFlow.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackOverFlow.security.entities.AnswerVote;
@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote,Long> {
}
