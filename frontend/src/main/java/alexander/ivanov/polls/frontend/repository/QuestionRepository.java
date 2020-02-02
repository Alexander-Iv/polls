package alexander.ivanov.polls.frontend.repository;

import alexander.ivanov.polls.frontend.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
