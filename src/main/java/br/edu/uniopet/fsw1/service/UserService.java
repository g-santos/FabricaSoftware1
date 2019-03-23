package br.edu.uniopet.fsw1.service;


import br.edu.uniopet.fsw1.model.User;
import br.edu.uniopet.fsw1.payload.JwtAuthenticationResponse;
import br.edu.uniopet.fsw1.repository.UserRepository;
import br.edu.uniopet.fsw1.security.JwtTokenProvider;
import br.edu.uniopet.fsw1.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(User userParam) {
        String loginUser = userParam.getUsername();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userParam.getUsername(),
                        userParam.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        User user = userRepository.findByUsernameOrEmail(loginUser, loginUser).get();
        UserPrincipal userPrincipal = new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());

        return ResponseEntity.ok()
                .body(new JwtAuthenticationResponse(jwt, userPrincipal));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public boolean checkUsernameAvailability( String username) {
        return !userRepository.existsByUsername(username);
    }

    public boolean checkEmailAvailability(String email) {
        return !userRepository.existsByEmail(email);
    }

    public boolean isUser(String username) {
        String user;
        boolean isAvailable = userRepository.existsByEmailOrUsername(username, username);

        if (isAvailable) {
            user = userRepository.findByUsernameOrEmail(username, username).get().getUsername();
            return true;
        }
        else
            return false;
    }

    public ResponseEntity<User> signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);

        return ResponseEntity.created(null).body(user);
    }

    public User findById(Long idUser){
        return userRepository.findById(idUser).get();
    }

    public User findByName(String username){
        return userRepository.findByUsernameOrEmail(username, username).get();
    }

    public void delete(Long idUser){
        userRepository.deleteById(idUser);
    }
}
