package stackOverFlow.security.services.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stackOverFlow.security.dtos.AnswerVoteDto;
import stackOverFlow.security.dtos.QuestionVoteDto;
import stackOverFlow.security.entities.*;
import stackOverFlow.security.enums.VoteType;
import stackOverFlow.security.repositories.*;

import java.util.Optional;

@Service
public class QuestionVoteServiceImpl implements QuestionVoteService{
    @Autowired
    QuestionVoteRepository questionVoteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired
    AnswerVoteRepository answerVoteRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public QuestionVoteDto addVoteToQuestion(QuestionVoteDto questionVoteDto) {
        Optional<User> optionalUser=userRepository.findById(questionVoteDto.getUserId());
        Optional<Questions> optionalQuestions=questionsRepository.findById(questionVoteDto.getQuestionId());
        if(optionalUser.isPresent() && optionalQuestions.isPresent()){
            QuestionVote questionVote=new QuestionVote();
            Questions existingQuestion=optionalQuestions.get();

            questionVote.setVoteType(questionVoteDto.getVoteType());
            if(questionVote.getVoteType()== VoteType.UPVOTE){
                existingQuestion.setVoteCount(existingQuestion.getVoteCount()+1);
            }
            else{
                existingQuestion.setVoteCount(existingQuestion.getVoteCount()-1);
            }
            questionsRepository.save(existingQuestion);
            questionVote.setUser(optionalUser.get());
            questionVote.setQuestion(optionalQuestions.get());
            QuestionVote votedQuestion=questionVoteRepository.save(questionVote);
            QuestionVoteDto createdQuestionVoteDto=new QuestionVoteDto();
            createdQuestionVoteDto.setId(votedQuestion.getId());
            return createdQuestionVoteDto;
        }
        return null;
    }

    @Override
    public AnswerVoteDto addVoteToAnswer(AnswerVoteDto answerVoteDto) {
        Optional<User> optionalUser=userRepository.findById(answerVoteDto.getUserId());
        Optional<Answers> optionalAnswers=answerRepository.findById(answerVoteDto.getAnswerId());
        if(optionalAnswers.isPresent() && optionalUser.isPresent()){
            Answers existingAnswer=optionalAnswers.get();
            User existingUser=optionalUser.get();
            AnswerVote answerVote=new AnswerVote();
            answerVote.setVoteType(answerVoteDto.getVoteType());
            if(answerVote.getVoteType()==VoteType.UPVOTE){
                existingAnswer.setVoteCount(existingAnswer.getVoteCount()+1);
            }
            else {
                existingAnswer.setVoteCount(existingAnswer.getVoteCount()+1);

            }
            answerRepository.save(existingAnswer);
            answerVote.setUser(optionalUser.get());
            answerVote.setAnswers(optionalAnswers.get());
            AnswerVote createdAnswerVote=answerVoteRepository.save(answerVote);
            AnswerVoteDto createdAnswerVoteDto=new AnswerVoteDto();
            createdAnswerVoteDto.setId(createdAnswerVote.getId());
            return createdAnswerVoteDto;

        }
        return null;
    }
}
