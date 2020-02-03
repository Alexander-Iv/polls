package alexander.ivanov.polls.frontend.repository;

import alexander.ivanov.polls.frontend.model.Poll;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends /*org.springframework.data.repository.Repository<Poll, Long>*/CrudRepository<Poll, Long>, JpaSpecificationExecutor<Poll> {
    //List<Poll> findAll();
    //Optional<Poll> findById(Long id);
    //void save(Poll poll);
    //void delete(Poll poll);
}
