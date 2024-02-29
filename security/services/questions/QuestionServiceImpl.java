package stackOverFlow.security.services.questions;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stackOverFlow.security.dtos.*;
import stackOverFlow.security.entities.*;
import stackOverFlow.security.enums.VoteType;
import stackOverFlow.security.repositories.AnswerRepository;
import stackOverFlow.security.repositories.ImageRepository;
import stackOverFlow.security.repositories.QuestionsRepository;
import stackOverFlow.security.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{

    private static final int SEARCH_RESULT_PER_PAGE=5;
    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    ImageRepository imageRepository;
    @Override
    public QuestionDto addQuestion(QuestionDto questionDto) {
      Optional<User> user=userRepository.findById(questionDto.getUserid());
      if(user.isPresent()){
          Questions questions=new Questions();
          questions.setTitle(questionDto.getTitle());
          questions.setBody(questionDto.getBody());
          questions.setTags(questionDto.getTags());
          questions.setUser(user.get());

          questions.setCreatedDate(new Date());
          Questions addedQuestion=questionsRepository.save(questions);
          QuestionDto createdQuestionDto=new QuestionDto();
          createdQuestionDto.setId(addedQuestion.getId());
          createdQuestionDto.setTitle(addedQuestion.getTitle());
          createdQuestionDto.setBody(addedQuestion.getBody());
          createdQuestionDto.setTags(addedQuestion.getTags());
          return createdQuestionDto;
      }
        return null;
    }

    @Override
    public AllQuestionResponseDto getAllQuestions(int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber,SEARCH_RESULT_PER_PAGE);
        Page<Questions> questionsPage=questionsRepository.findAll(pageable);

        AllQuestionResponseDto allQuestionResponseDto=new AllQuestionResponseDto();
        allQuestionResponseDto.setQuestionDtoList(questionsPage.getContent().stream().map(Questions::getQuestionDto).collect(Collectors.toList()));
        allQuestionResponseDto.setPageNumber(questionsPage.getPageable().getPageNumber());
        allQuestionResponseDto.setTotalpages(questionsPage.getTotalPages());

        return allQuestionResponseDto;
    }

    @Override
    public QuestionSearchResponseDto getLatestQuestions(int pageNum) {
        Pageable pageable=PageRequest.of(pageNum,SEARCH_RESULT_PER_PAGE);
        Page<Questions> questionsPage;
        questionsPage=questionsRepository.findAllByOrderByCreatedDateDesc(pageable);
        QuestionSearchResponseDto questionSearchResponseDto=new QuestionSearchResponseDto();
        questionSearchResponseDto.setQuestionDtoList(questionsPage.stream().map(Questions::getQuestionDto).collect(Collectors.toList()));
        questionSearchResponseDto.setTotalpages(questionsPage.getTotalPages());
        questionSearchResponseDto.setPageNumber(questionsPage.getPageable().getPageNumber());
        return questionSearchResponseDto;

    }

    @Override
    public SingleQuestionDto getQuestionById(long questionId,long userId) {
        Optional<Questions> optionalQuestions=questionsRepository.findById(questionId);
        if(optionalQuestions.isPresent()) {
            SingleQuestionDto singleQuestionDto = new SingleQuestionDto();
            Questions existingQuestion=optionalQuestions.get();
            Optional<QuestionVote> optionalQuestionVote=existingQuestion.getQuestionVotes().stream().filter(vote->vote.getUser().getId().equals(userId)).findFirst();
            QuestionDto questionDto=optionalQuestions.get().getQuestionDto();
            questionDto.setVoted(0);
            if(optionalQuestionVote.isPresent()){
                if(optionalQuestionVote.get().getVoteType().equals(VoteType.UPVOTE)){
                    questionDto.setVoted(1);
                }
                else{
                    questionDto.setVoted(-1);
                }
            }
            List<AnswerDto> answerDtoList=new ArrayList<>();
            singleQuestionDto.setQuestionDto(questionDto);
            List<Answers> answersList=answerRepository.findAllByQuestionId(questionId);
            for(Answers answers:answersList){
                if(answers.isApproved()){
                    singleQuestionDto.getQuestionDto().setHasApprovedAnswer(true);
                }
                AnswerDto answerDto=answers.getAnswerDto();
                Optional<AnswerVote> optionalAnswerVote=answers.getAnswerVotes().stream().filter(
                        answerVote -> answerVote.getUser().getId().equals(userId)
                ).findFirst();
                answerDto.setVoted(0);
                if(optionalAnswerVote.isPresent()){
                    if(optionalAnswerVote.get().getVoteType().equals(VoteType.UPVOTE)){
                        answerDto.setVoted(1);
                    }
                    else {
                        answerDto.setVoted(-1);
                    }
                }

                answerDto.setFile(imageRepository.findByAnswers(answers));


                //comment section
                List<CommentDto> commentDtoList=new ArrayList<>();
                answers.getCommentList().forEach(comment -> {
                    CommentDto commentDto=comment.getCommentDto();
                    commentDtoList.add(commentDto);
                });
                answerDto.setCommentDtoList(commentDtoList);
                answerDtoList.add(answerDto);

            }
            singleQuestionDto.setAnswerDtoList(answerDtoList);
            return singleQuestionDto;

        }

        return null;
    }

    @Override
    public AllQuestionResponseDto getQuestionsByUserId(Long userId, int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber,SEARCH_RESULT_PER_PAGE);
        Page<Questions> questionsPage=questionsRepository.findAllByUserId(userId,pageable);

        AllQuestionResponseDto allQuestionResponseDto=new AllQuestionResponseDto();
        allQuestionResponseDto.setQuestionDtoList(questionsPage.getContent().stream().map(Questions::getQuestionDto).collect(Collectors.toList()));
        allQuestionResponseDto.setPageNumber(questionsPage.getPageable().getPageNumber());
        allQuestionResponseDto.setTotalpages(questionsPage.getTotalPages());

        return allQuestionResponseDto;
    }

    @Override
    public QuestionSearchResponseDto getQuestionsByTitle(String title, int pageNum) {
        Pageable pageable=PageRequest.of(pageNum,SEARCH_RESULT_PER_PAGE);
        Page<Questions> questionsPage;
        if(title==null || title.equals("null")){
            questionsPage=questionsRepository.findAll(pageable);
        }
        else {
            questionsPage = questionsRepository.findAllByTitleContaining(title, pageable);
        }
        QuestionSearchResponseDto questionSearchResponseDto=new QuestionSearchResponseDto();
        questionSearchResponseDto.setQuestionDtoList(questionsPage.stream()
                .map(Questions::getQuestionDto)
                .collect(Collectors.toList()));
        questionSearchResponseDto.setPageNumber(questionsPage.getPageable().getPageNumber());
        questionSearchResponseDto.setTotalpages(questionsPage.getTotalPages());
        return questionSearchResponseDto;

    }
}
