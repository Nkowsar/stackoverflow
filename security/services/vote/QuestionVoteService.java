package stackOverFlow.security.services.vote;

import stackOverFlow.security.dtos.AnswerVoteDto;
import stackOverFlow.security.dtos.QuestionVoteDto;

public interface QuestionVoteService {
    QuestionVoteDto addVoteToQuestion(QuestionVoteDto questionVoteDto);

    AnswerVoteDto addVoteToAnswer(AnswerVoteDto answerVoteDto);
}
