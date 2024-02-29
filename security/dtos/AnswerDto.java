package stackOverFlow.security.dtos;

import lombok.Data;
import stackOverFlow.security.entities.Comment;
import stackOverFlow.security.entities.Image;

import java.util.Date;
import java.util.List;

@Data
public class AnswerDto {

    private long id;
    private String body;
    private Date createdDate;
    private long userId;
    private long questionId;
    private Image file;
    private String username;
    private boolean approved;
    private int voted;
    private Integer voteCount;
    private List<CommentDto> commentDtoList;
}
