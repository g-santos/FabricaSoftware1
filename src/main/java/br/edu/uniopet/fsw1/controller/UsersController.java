package br.edu.uniopet.fsw1.controller;


import br.edu.uniopet.fsw1.model.User;
import br.edu.uniopet.fsw1.security.UserPrincipal;
import br.edu.uniopet.fsw1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> list(){
        return userService.findAll();
    }

    @GetMapping("/me")
    public User getCurrentUser(UserPrincipal currentUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByName(authentication.getName());
    }

    @GetMapping("/checkUsernameAvailability")
    public boolean checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return userService.checkUsernameAvailability(username);
    }

    @GetMapping("/checkEmailAvailability")
    public boolean checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userService.checkEmailAvailability(email);
    }

    @GetMapping("/isUser")
    public boolean isUser(@RequestParam(value = "username") String username) {
        return userService.isUser(username);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @GetMapping("/{idUser}")
    public User findById(@PathVariable Long idUser){
        return userService.findById(idUser);
    }

    @DeleteMapping("/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idUser){
        userService.delete(idUser);
    }
}
