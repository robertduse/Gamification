package app.gamification.model.request;

import app.gamification.model.quiz.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuizRequest {
    private final Quiz quiz;
    private final String username;
}
