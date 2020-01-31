package alexander.ivanov.polls.frontend.service;

import alexander.ivanov.polls.frontend.model.Poll;
import alexander.ivanov.polls.frontend.repository.PollRepository;
import alexander.ivanov.polls.frontend.util.Constants;
import alexander.ivanov.polls.frontend.util.DtoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static alexander.ivanov.polls.frontend.util.Constants.POLL_NOT_FOUND;

@Service
public class PollService {
    private static final Logger logger = LoggerFactory.getLogger(PollService.class);
    private PollRepository pollRepository;

    @Autowired
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public List<Poll> getPolls() {
        List<Poll> polls = new ArrayList<>();
        pollRepository
                .findAll()
                .forEach(polls::add);
        if (polls.isEmpty()) {
            throw new RuntimeException(Constants.POLLS_NOT_FOUND);
        }

        polls.forEach(poll -> {
            logger.info("poll = {}", poll);
        });

        return polls;
    }

    public Poll getPollById(String id) {
        Long valueId = DtoUtils.stringToLongId(id);
        return pollRepository.findById(valueId)
                .orElseThrow(() -> new RuntimeException(POLL_NOT_FOUND));
    }
}
