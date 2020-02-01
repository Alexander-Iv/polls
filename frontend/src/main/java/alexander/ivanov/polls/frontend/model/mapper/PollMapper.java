package alexander.ivanov.polls.frontend.model.mapper;

import alexander.ivanov.polls.frontend.model.Poll;
import alexander.ivanov.polls.frontend.model.Question;
import alexander.ivanov.polls.frontend.model.dto.PollDto;
import alexander.ivanov.polls.frontend.model.dto.QuestionDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;

public class PollMapper {
    private static final TypeMap<PollDto, Poll> mapper = new ModelMapper()
            .createTypeMap(PollDto.class, Poll.class)
            .addMappings(mapping -> mapping.using(new QuestionsConverter()).map(PollDto::getQuestions, Poll::setQuestions));

    public static Poll map(PollDto dto) {
        return mapper.map(dto);
    }

    private static class QuestionsConverter extends AbstractConverter<List<QuestionDto>, List<Question>> {
        @Override
        protected List<Question> convert(List<QuestionDto> source) {
            return source.stream()
                    .map(QuestionMapper::map)
                    .collect(Collectors.toList());
        }
    }
}
