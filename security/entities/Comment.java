package stackOverFlow.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import stackOverFlow.security.dtos.CommentDto;

import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "answer_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Answers answers;

    public CommentDto getCommentDto(){
        CommentDto commentDto=new CommentDto();
        commentDto.setId(id);
        commentDto.setBody(body);
        commentDto.setUsername(user.getFullName());
        commentDto.setCreatedDate(createdDate);
        commentDto.setUserId(user.getId());
        commentDto.setAnswerId(answers.getId());
        return commentDto;
    }

}
