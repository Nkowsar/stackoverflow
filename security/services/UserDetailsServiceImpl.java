package stackOverFlow.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stackOverFlow.security.entities.User;
import stackOverFlow.security.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(email);
//        User user=new User();
//        user.setEmail("ergtreef@ergtr.com");
//        user.setPassword("dfgrthrt");
//        user.setFullName("ergrth");
        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not exists");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),user.get().getPassword(),new ArrayList<>());
    }
}
