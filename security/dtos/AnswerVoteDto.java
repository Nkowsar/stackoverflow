package stackOverFlow.security.dtos;

import lombok.Data;
import stackOverFlow.security.enums.VoteType;
@Data
public class AnswerVoteDto {
    private Long id;
    private VoteType voteType;
    private Long userId;
    private Long answerId;
}
