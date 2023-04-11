package app.gamification.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuizAnswer {
    private final String username;
    private final String question;
    private final String answer;
}
