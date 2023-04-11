package app.gamification.service;

import app.gamification.model.quiz.Quiz;
import app.gamification.model.user.User;
import app.gamification.model.quiz.QuizBase;
import app.gamification.model.request.QuizAnswer;
import app.gamification.model.request.QuizRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private static final int MIN_TOKEN_TO_ADD_QUIZ = 5;

    private final UserService userService;

    private final List<Quiz> quizList = new ArrayList<>();

    public QuizService(UserService userService) {
        this.userService = userService;
        this.quizList.add(new Quiz("Care este capitala Romaniei?", "Bucuresti", 5));
        this.quizList.add(new Quiz("Care este resedinta de judet a Bihorului?", "Oradea", 10));
        this.quizList.add(new Quiz("Ce înseamnă prescurtarea www?", "world wide web", 15));
        this.quizList.add(new Quiz("Generația de computere Macintosh este creată de?", "apple", 3));

    }

    public List<QuizBase> getQuizQuestions() {
        return quizList.stream().map(quiz -> new QuizBase(quiz.getQuestion(), quiz.getRewardToken())).collect(Collectors.toList());
    }

    public Optional<Quiz> answer(QuizAnswer quizAnswer) {
        Optional<Quiz> quizToAnswer = this.quizList.stream().filter(quiz -> quiz.getQuestion().equals(quizAnswer.getQuestion())).findFirst();
        if (quizToAnswer.isEmpty()) {
            throw new RuntimeException("There is no matching question in quiz list.");
        }
        return quizToAnswer.get().isCorrectAnswer(quizAnswer.getAnswer());
    }

    public void addQuiz(QuizRequest quizRequest) throws Exception {
        User userLoggedIn = this.userService.isUserLoggedIn(quizRequest.getUsername());
        if (userLoggedIn.getWonTokens() < MIN_TOKEN_TO_ADD_QUIZ) {
            throw new Exception("User doesn't have enough tokens to add a quiz. Please answer more questions.");
        }
        this.quizList.add(quizRequest.getQuiz());
    }
}
