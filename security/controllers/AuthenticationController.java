package stackOverFlow.security.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stackOverFlow.security.dtos.AuthenticationRequest;
import stackOverFlow.security.dtos.AuthenticationResponse;
import stackOverFlow.security.entities.User;
import stackOverFlow.security.repositories.UserRepository;
import stackOverFlow.security.utils.JwtUtil;

import java.io.IOException;
import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";

    @PostMapping("/authentication")

    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
                try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
                }
                catch (BadCredentialsException e){
                    throw new BadCredentialsException("Incorrect email or password");
                }catch (DisabledException disabledException){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND,"user is not created");
                    return;
                }
                final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        Optional<User> userOptional=userRepository.findByEmail(userDetails.getUsername());

                final String jwt=jwtUtil.generateToken(userDetails);

                if(userOptional.isPresent()){
                    response.getWriter().write(new JSONObject()
                            .put("userid",userOptional.get().getId())
                            .toString()
                    );
                }

                response.addHeader("Access-Control-Expose-Headers","Authorization");
                response.setHeader("Access-Control-Allow-Headers","Authorization, x-PINGOTHER, x-Requested-With, Content-Type, Accept, x-Custom-header");
                response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);

    }
}
