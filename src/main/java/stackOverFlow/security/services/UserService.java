package stackOverFlow.security.services;

import stackOverFlow.security.dtos.SignupDto;
import stackOverFlow.security.dtos.UserDto;

public interface UserService {
    UserDto createUser(SignupDto signupDto);

    boolean hasUserWithEmail(String email);
}
