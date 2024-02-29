package stackOverFlow.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import stackOverFlow.security.dtos.AnswerDto;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Column(name = "body" , length = 512)
    private String body;
    private Date createdDate;

    private boolean approved=false;

    @Column(columnDefinition = "integer default 0")
    private Integer voteCount=0;

    @OneToMany(mappedBy = "answers",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AnswerVote> answerVotes;

    @OneToMany(mappedBy = "answers",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> commentList;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "question_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Questions question;

    public AnswerDto getAnswerDto(){
        AnswerDto answerDto=new AnswerDto();
        answerDto.setId(id);
        answerDto.setBody(body);
        answerDto.setUserId(user.getId());
        answerDto.setUsername(user.getFullName());
        answerDto.setQuestionId(question.getId());
        answerDto.setApproved(approved);
        answerDto.setCreatedDate(createdDate);
        answerDto.setVoteCount(voteCount);
        return answerDto;
    }
}
