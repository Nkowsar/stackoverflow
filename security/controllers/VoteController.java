package stackOverFlow.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stackOverFlow.security.dtos.AnswerVoteDto;
import stackOverFlow.security.dtos.QuestionVoteDto;
import stackOverFlow.security.services.vote.QuestionVoteService;

@RestController
@RequestMapping("/api")
public class VoteController {
    @Autowired
    QuestionVoteService questionVoteService;

    @PostMapping("/vote")
    public ResponseEntity<?> addVoteToQuestion(@RequestBody QuestionVoteDto questionVoteDto){
        QuestionVoteDto createdquestionVoteDto=questionVoteService.addVoteToQuestion(questionVoteDto);
        if(createdquestionVoteDto==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdquestionVoteDto);
    }


    @PostMapping("/answer-vote")
    public ResponseEntity<?> addVoteToAnswer(@RequestBody AnswerVoteDto answerVoteDto){
        AnswerVoteDto createdAnserVoteDto=questionVoteService.addVoteToAnswer(answerVoteDto);
        if(createdAnserVoteDto==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnserVoteDto);

    }

}
