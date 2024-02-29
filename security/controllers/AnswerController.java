package stackOverFlow.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackOverFlow.security.dtos.AnswerDto;
import stackOverFlow.security.dtos.CommentDto;
import stackOverFlow.security.services.answer.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {


    @Autowired
    AnswerService answerService;
    @PostMapping
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto){

        AnswerDto createdAnswerDto=answerService.saveAnswer(answerDto);
        if(createdAnswerDto==null){
            return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswerDto);

    }
    @GetMapping("/answerapprove/{answerId}")
    public ResponseEntity<AnswerDto> approveAnswer(@PathVariable Long answerId){
        AnswerDto approvedAnswer=answerService.approveAnswer(answerId);
        if(approvedAnswer==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(approvedAnswer);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> postCommentToAnswer(@RequestBody CommentDto commentDto){
        CommentDto postedCommentDto=answerService.postCommentToAnswer(commentDto);
        if(postedCommentDto==null){
            return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postedCommentDto);
    }
}
