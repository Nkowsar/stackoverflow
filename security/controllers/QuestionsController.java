package stackOverFlow.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackOverFlow.security.dtos.AllQuestionResponseDto;
import stackOverFlow.security.dtos.QuestionDto;
import stackOverFlow.security.dtos.QuestionSearchResponseDto;
import stackOverFlow.security.dtos.SingleQuestionDto;
import stackOverFlow.security.services.questions.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionsController {

    private final QuestionService questionService;

    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping("/question")
    public ResponseEntity<?> postQuestion(@RequestBody QuestionDto questionDto){

        QuestionDto addedQuestionDto=questionService.addQuestion(questionDto);
        if(addedQuestionDto==null){
            return new ResponseEntity<>("user not exists,sign up first to post a question", HttpStatus.BAD_REQUEST);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addedQuestionDto);
    }

    @PostMapping("/questions/{pageNumber}")
    public ResponseEntity<AllQuestionResponseDto> getAllQuestions(@PathVariable int pageNumber){
        AllQuestionResponseDto allQuestionResponseDto=questionService.getAllQuestions(pageNumber);
        return ResponseEntity.ok(allQuestionResponseDto);

    }

    @PostMapping("/questions/{userId}/{pageNumber}")
    public ResponseEntity<AllQuestionResponseDto> getQuestionsByUserId(@PathVariable Long userId,  @PathVariable int pageNumber){
        AllQuestionResponseDto allQuestionResponseDto=questionService.getQuestionsByUserId(userId,pageNumber);
        return ResponseEntity.ok(allQuestionResponseDto);

    }
    @GetMapping("/question/{questionId}/{userId}")
    public ResponseEntity<?> getQuestionById(@PathVariable long questionId,@PathVariable long userId){
        SingleQuestionDto questionDto=questionService.getQuestionById(questionId,userId);

        if(questionDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(questionDto);

    }
    @GetMapping("/question/latest/{pageNum}")
    public ResponseEntity<?> getLatestQuestions(@PathVariable int pageNum){
        QuestionSearchResponseDto questionSearchResponseDto=questionService.getLatestQuestions(pageNum);
        if(questionSearchResponseDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questionSearchResponseDto);
    }
    @GetMapping("/search/{title}/{pageNumber}")
    public ResponseEntity<?> searchQuestionByTitle(@PathVariable String title,@PathVariable int pageNumber){
       QuestionSearchResponseDto questionSearchResponseDto= questionService.getQuestionsByTitle(title,pageNumber);
       if(questionSearchResponseDto==null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(questionSearchResponseDto);
    }
}
