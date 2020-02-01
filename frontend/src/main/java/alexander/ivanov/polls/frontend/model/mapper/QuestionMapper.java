package alexander.ivanov.polls.frontend.model.mapper;

import alexander.ivanov.polls.frontend.model.Question;
import alexander.ivanov.polls.frontend.model.dto.QuestionDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class QuestionMapper {
    private static final TypeMap<QuestionDto, Question> mapper = new ModelMapper().createTypeMap(QuestionDto.class, Question.class);

    public static Question map(QuestionDto dto) {
        return mapper.map(dto);
    }
}
