package stackOverFlow.security.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionDto {
    private Long id;
    private String title;
    private String  body;
    private List<String> tags;
    private Long userid;
    private String username;
    private Date createdDate;
    private Integer voteCount;
    private int voted;
    private boolean hasApprovedAnswer=false;
}
