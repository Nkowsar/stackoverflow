package stackOverFlow.security.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackOverFlow.security.entities.Questions;
@Repository
public interface QuestionsRepository extends JpaRepository<Questions,Long> {
    Page<Questions> findAllByUserId(Long userId, Pageable pageable);


    Page<Questions> findAllByTitleContaining(String title, Pageable pageable);

    Page<Questions> findAllByOrderByCreatedDateDesc(Pageable pageable);

    Page<Questions> findAllByOrderByCreatedDate(Pageable pageable);

    Page<Questions> findAllByOrderByCreatedDateAsc(Pageable pageable);
}
