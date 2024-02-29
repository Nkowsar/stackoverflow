package stackOverFlow.security.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Long id;
    private String body;
    private Date createdDate;
    private Long userId;
    private Long answerId;
    private String username;
}
