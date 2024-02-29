package stackOverFlow.security.services.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stackOverFlow.security.dtos.AnswerDto;
import stackOverFlow.security.dtos.CommentDto;
import stackOverFlow.security.entities.Answers;
import stackOverFlow.security.entities.Comment;
import stackOverFlow.security.entities.Questions;
import stackOverFlow.security.entities.User;
import stackOverFlow.security.repositories.AnswerRepository;
import stackOverFlow.security.repositories.CommentRepository;
import stackOverFlow.security.repositories.QuestionsRepository;
import stackOverFlow.security.repositories.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService{
    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public AnswerDto saveAnswer(AnswerDto answerDto) {

        Optional<User> optionalUser=userRepository.findById(answerDto.getUserId());
        Optional<Questions> optionalQuestions=questionsRepository.findById(answerDto.getQuestionId());
        if(optionalUser.isPresent() && optionalQuestions.isPresent()){
            Answers answers=new Answers();
            answers.setBody(answerDto.getBody());
            answers.setCreatedDate(new Date());
            answers.setUser(optionalUser.get());
            answers.setQuestion(optionalQuestions.get());
            Answers createdAnswer=answerRepository.save(answers);
            AnswerDto createdAnswerDto=new AnswerDto();
            createdAnswerDto.setId(createdAnswer.getId());
            return createdAnswerDto;
        }

        return null;
    }

    @Override
    public CommentDto postCommentToAnswer(CommentDto commentDto) {
        Optional<Answers> optionalAnswers=answerRepository.findById(commentDto.getAnswerId());
        Optional<User> optionalUser=userRepository.findById(commentDto.getUserId());
        if(optionalAnswers.isPresent() && optionalUser.isPresent()){
            Comment comment=new Comment();
            comment.setBody(commentDto.getBody());
            comment.setAnswers(optionalAnswers.get());
            comment.setUser(optionalUser.get());
            comment.setCreatedDate(new Date());
            Comment postedComment=commentRepository.save(comment);
            CommentDto createdCommentDto=new CommentDto();
            createdCommentDto.setId(postedComment.getId());
            return createdCommentDto;
        }
        return null;
    }

    @Override
    public AnswerDto approveAnswer(Long answerId) {
        Optional<Answers> optionalAnswers=answerRepository.findById(answerId);
        if(optionalAnswers.isPresent()){
            Answers answers=optionalAnswers.get();
            answers.setApproved(true);
            Answers updatedAnswer=answerRepository.save(answers);
            AnswerDto updatedAnswerDto=new AnswerDto();
            updatedAnswerDto.setId(updatedAnswer.getId());
            return updatedAnswerDto;

        }
        return null;
    }


}
