package app.gamification.model.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QuizBase {
    private String question;
    private int rewardToken;
}
