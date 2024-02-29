package stackOverFlow.security.services.questions;

import stackOverFlow.security.dtos.AllQuestionResponseDto;
import stackOverFlow.security.dtos.QuestionDto;
import stackOverFlow.security.dtos.QuestionSearchResponseDto;
import stackOverFlow.security.dtos.SingleQuestionDto;

public interface QuestionService {
    QuestionDto addQuestion(QuestionDto questionDto);

    AllQuestionResponseDto getAllQuestions(int pageNumber);

    SingleQuestionDto getQuestionById(long questionId,long userId);

    AllQuestionResponseDto getQuestionsByUserId(Long userId, int pageNumber);

    QuestionSearchResponseDto getQuestionsByTitle(String title,int pageNum);

    QuestionSearchResponseDto getLatestQuestions(int pageNum);
}
