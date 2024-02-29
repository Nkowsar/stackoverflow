package stackOverFlow.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import stackOverFlow.security.dtos.SignupDto;
import stackOverFlow.security.dtos.UserDto;
import stackOverFlow.security.entities.User;
import stackOverFlow.security.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(SignupDto signupDto) {
        User user=new User();
        user.setFullName(signupDto.getFullName());
        user.setEmail(signupDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDto.getPassword()));
        User createUser1=userRepository.save(user);
        UserDto createUserdto=new UserDto();
        createUserdto.setId(createUser1.getId());
        return createUserdto;
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
