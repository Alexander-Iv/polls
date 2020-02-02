package alexander.ivanov.polls.frontend.repository;

import alexander.ivanov.polls.frontend.model.Poll;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PollRepository extends /*CrudRepository<Poll, Long>,*/ /*JpaRepository<Poll, Long>*/org.springframework.data.repository.Repository<Poll, Long>, JpaSpecificationExecutor<Poll> {
    //List<Poll> findAllByNameLikeAndStartDateLikeAndEndDateLikeAndActivityEquals(String name, Date startDate, Date endDate, Boolean activity);
    /*@Query("select p.name, p.startDate, p.endDate, p.activity, (select q from Question q where q.qstn_id = p.poll_id order by q.orderNum asc) as questions" +
            " from Poll p where p.name like :name ")*/
    List<Poll> findAll();
    List<Poll> findAll(Pageable pageable);

    //@Query("select p from Poll p where p.name like #{filter.name} and p.startDate like #{filter.startDate} and p.endDate like #{filter.endDate} and p.activity like #{filter.activity}")
    //List<Poll> findAllByFilter(@Param("filter") PollFilterDto filter, Pageable pageable);
    //@Query("select p from Poll where p.name like :name and p.startDate like :startDate and p.endDate like :endDate and p.activity like :activity")
    List<Poll> findAllByNameLikeAndStartDateLikeAndEndDateLikeAndActivityLike(String name, Date startDate, Date endDate, Boolean activity, Pageable pageable);
    /*List<Poll> findAllByNameLike(String name, Pageable pageable);
    List<Poll> findAllByStartDateLessThanAndEndDateLessThanEqual(Date startDate, Date endDate, Pageable pageable);
    List<Poll> findAllByActivityEquals(Boolean activity, Pageable pageable);*/
    Optional<Poll> findById(Long id);
    void save(Poll poll);
}
