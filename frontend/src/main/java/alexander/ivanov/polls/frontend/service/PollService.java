package alexander.ivanov.polls.frontend.service;

import alexander.ivanov.polls.frontend.model.Poll;
import alexander.ivanov.polls.frontend.model.Question;
import alexander.ivanov.polls.frontend.model.dto.PollDto;
import alexander.ivanov.polls.frontend.model.mapper.PollMapper;
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
    private QuestionService questionService;

    @Autowired
    public PollService(PollRepository pollRepository, QuestionService questionService) {
        this.pollRepository = pollRepository;
        this.questionService = questionService;
    }

    public List<Poll> getPolls() {
        List<Poll> polls = new ArrayList<>();
        pollRepository
                .findAll()
                .forEach(polls::add);
        if (polls.isEmpty()) {
            throw new RuntimeException(Constants.POLLS_NOT_FOUND);
        }

        polls.forEach(poll -> sortQuestion(poll.getQuestions()));

        return polls;
    }

    public Poll getPollById(String id) {
        Long valueId = DtoUtils.stringToLongId(id);
        return pollRepository.findById(valueId)
                .orElseThrow(() -> new RuntimeException(POLL_NOT_FOUND));
    }

    @Transactional
    public Poll createPoll(PollDto pollDto) {
        Poll mappedPoll = PollMapper.map(pollDto);
        logger.info("mappedPoll = {}", mappedPoll);

        mappedPoll.getQuestions()
                .forEach(question -> questionService.createQuestion(question));

        pollRepository.save(mappedPoll);
        return mappedPoll;
    }

    private void sortQuestion(List<Question> questions) {
        questions.sort((o1, o2) -> Long.compare(o2.getId(), o1.getId()));
    }
}
