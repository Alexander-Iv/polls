package alexander.ivanov.polls.frontend.repository;

import alexander.ivanov.polls.frontend.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
