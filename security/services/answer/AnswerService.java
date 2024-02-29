package stackOverFlow.security.services.answer;

import stackOverFlow.security.dtos.AnswerDto;
import stackOverFlow.security.dtos.CommentDto;

public interface AnswerService {
    AnswerDto saveAnswer(AnswerDto answerDto);
    AnswerDto approveAnswer(Long answerId);

    CommentDto postCommentToAnswer(CommentDto commentDto);
}
