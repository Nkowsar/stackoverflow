package stackOverFlow.security.dtos;

import lombok.Data;
import stackOverFlow.security.entities.Questions;
import stackOverFlow.security.entities.User;
import stackOverFlow.security.enums.VoteType;

@Data
public class QuestionVoteDto {

    private Long id;
    private VoteType voteType;
    private Long userId;
    private Long questionId;
}
