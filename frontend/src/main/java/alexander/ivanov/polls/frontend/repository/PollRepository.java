package alexander.ivanov.polls.frontend.repository;

import alexander.ivanov.polls.frontend.model.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {
}
