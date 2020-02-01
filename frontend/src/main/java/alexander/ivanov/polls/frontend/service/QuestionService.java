package alexander.ivanov.polls.frontend.service;

import alexander.ivanov.polls.frontend.model.Question;
import alexander.ivanov.polls.frontend.model.dto.QuestionDto;
import alexander.ivanov.polls.frontend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
}
