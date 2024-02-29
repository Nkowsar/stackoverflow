package stackOverFlow.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import stackOverFlow.security.dtos.QuestionDto;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name="body",length = 512)
    private String body;

    private String title;
    private Date createdDate;

    @ElementCollection(targetClass = String.class)
    private List<String> tags;

    @Nonnull
    @Column(columnDefinition = "integer default 0")
    private Integer voteCount=0;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<QuestionVote> questionVotes;

    public QuestionDto getQuestionDto(){
        QuestionDto questionDto=new QuestionDto();
        questionDto.setId(id);
        questionDto.setTitle(title);
        questionDto.setBody(body);
        questionDto.setTags(tags);
        questionDto.setCreatedDate(createdDate);
        questionDto.setUserid(user.getId());
        questionDto.setVoteCount(voteCount);
        questionDto.setUsername(user.getFullName());
        return questionDto;
    }
}
