package app.gamification.controller;

import app.gamification.service.QuizService;
import app.gamification.model.user.User;
import app.gamification.model.user.UserRanking;
import app.gamification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final QuizService quizService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean existsUser = userService.existsUser(user);
        if (existsUser) {
            return ResponseEntity.ok(quizService.getQuizQuestions());
        } else {
            return ResponseEntity.badRequest().body("User doesn't exists please create one first.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean registered = userService.registerUser(user);
        return ResponseEntity.ok(registered ? "Registered user: " + user.getUsername() + ".Please login to start the game." : "Invalid username.");
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<UserRanking>> getRanking() {
        return ResponseEntity.ok(userService.getRanking());
    }
}
