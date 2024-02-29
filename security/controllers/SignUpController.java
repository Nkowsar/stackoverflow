package stackOverFlow.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stackOverFlow.security.dtos.SignupDto;
import stackOverFlow.security.dtos.UserDto;
import stackOverFlow.security.services.UserService;

@RestController
public class SignUpController {

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody(required = true) SignupDto signupDto){

        if(userService.hasUserWithEmail(signupDto.getEmail())){
            return new ResponseEntity<>("user already exists with this email "+signupDto.getEmail(),HttpStatus.NOT_ACCEPTABLE);
        }

           UserDto userDto= userService.createUser(signupDto);
           if(userDto==null){
               return new ResponseEntity<>("user not created", HttpStatus.BAD_REQUEST);
           }
           return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }


}
