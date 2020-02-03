package alexander.ivanov.polls.frontend.service;

import alexander.ivanov.polls.frontend.model.Poll;
import alexander.ivanov.polls.frontend.model.dto.PollDto;
import alexander.ivanov.polls.frontend.model.mapper.PollMapper;
import alexander.ivanov.polls.frontend.repository.PollRepository;
import alexander.ivanov.polls.frontend.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static alexander.ivanov.polls.frontend.util.MessageConstants.POLL_NOT_FOUND;

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
            throw new RuntimeException(MessageConstants.POLLS_NOT_FOUND);
        }

        return polls;
    }

    public List<Poll> getPolls(Map<String, String> params) {
        Specification<Poll> pollSpecifications = PollSpecificationUtils.getSpecificationFromParams(params);
        Sort sort = SortUtils.sortBy(params);
        Pageable pageable = PageUtils.getPageable(params, sort);

        List<Poll> polls = pollRepository.findAll(pollSpecifications, pageable).getContent();
        if (polls.isEmpty()) {
            throw new RuntimeException(MessageConstants.POLLS_NOT_FOUND);
        }

        return polls;
    }

    public Poll getPollById(String id) {
        Long parsedId = DtoUtils.toLong(id);
        Poll loadedPoll = pollRepository.findById(parsedId)
                .orElseThrow(() -> new RuntimeException(POLL_NOT_FOUND));
        return loadedPoll;
    }

    @Transactional
    public Boolean createOrUpdatePoll(PollDto pollDto) {
        Poll mappedPoll = PollMapper.map(pollDto);
        logger.info("mappedPoll = {}", mappedPoll);

        mappedPoll.getQuestions().forEach(question -> questionService.createOrUpdateQuestion(question));

        pollRepository.save(mappedPoll);
        return true;
    }

    @Transactional
    public Boolean deletePoll(String id) {
        Poll loadedPoll = getPollById(id);

        loadedPoll.getQuestions().forEach(question -> {
            questionService.deleteQuestion(question.getId());
        });

        pollRepository.deleteById(loadedPoll.getId());

        return true;
    }
}
