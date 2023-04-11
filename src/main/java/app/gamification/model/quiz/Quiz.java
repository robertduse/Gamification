package app.gamification.model.quiz;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Quiz extends QuizBase {

    private final String answer;

    public Quiz(String question, String answer, int rewardToken) {
        super(question, rewardToken);
        this.answer = answer;
    }

    public Optional<Quiz> isCorrectAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer) ? Optional.of(this) : Optional.empty();
    }
}
