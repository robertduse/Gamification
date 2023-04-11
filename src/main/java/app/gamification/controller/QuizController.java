package app.gamification.controller;

import app.gamification.model.quiz.Quiz;
import app.gamification.model.user.User;
import app.gamification.model.request.QuizAnswer;
import app.gamification.model.request.QuizRequest;
import app.gamification.service.QuizService;
import app.gamification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "quiz", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;

    @PostMapping("/answer")
    public ResponseEntity<?> answer(@RequestBody QuizAnswer quizAnswer) throws Exception {
        userService.isUserLoggedIn(quizAnswer.getUsername());
        Optional<Quiz> quiz = this.quizService.answer(quizAnswer);
        User user = null;
        if (quiz.isPresent()) {
            user = this.userService.addReward(quizAnswer.getUsername(), quiz.get().getRewardToken());
        }
        return ResponseEntity.ok(quiz.isPresent() ? user: "Raspuns gresit");
    }

    @PostMapping("/addQuiz")
    public ResponseEntity<?> addQuestion(@RequestBody QuizRequest quizRequest) throws Exception {
        this.quizService.addQuiz(quizRequest);
        return ResponseEntity.ok().build();
    }
}
