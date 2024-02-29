package stackOverFlow.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackOverFlow.security.entities.QuestionVote;
@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVote,Long> {
}
