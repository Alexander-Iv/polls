package alexander.ivanov.polls.frontend.repository;

import alexander.ivanov.polls.frontend.model.Poll;
import alexander.ivanov.polls.frontend.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAllByPoll(Poll poll);
}
