package app.gamification.service;

import app.gamification.repository.UserRepository;
import app.gamification.model.user.User;
import app.gamification.model.user.UserRanking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean existsUser(User user) {
        return userRepository.existsUser(user);
    }

    public boolean registerUser(User user) {
        if (userRepository.existsUser(user)) {
            return false;
        }
        return userRepository.addUser(user);
    }

    public List<User> getUsers() {
        return userRepository.getApplicationUsers().stream()
                .map(user -> new User(user.getUsername(), null, user.isLoggedIn(), user.getWonTokens()))
                .collect(Collectors.toList());
    }

    public User isUserLoggedIn(String username) throws Exception {
        return this.userRepository.isUserLoggedIn(username);
    }

    public User addReward(String username, int rewardToken) throws Exception {
        User userByUsername = this.userRepository.isUserLoggedIn(username);
        userByUsername.setWonTokens(userByUsername.getWonTokens() + rewardToken);
        return User.builder()
                .username(userByUsername.getUsername())
                .password(null)
                .isLoggedIn(userByUsername.isLoggedIn())
                .wonTokens(userByUsername.getWonTokens())
                .build();
    }

    public List<UserRanking> getRanking() {
        List<User> userSortedByToken = userRepository.getApplicationUsers()
                .stream()
                .sorted(Comparator.comparingInt(User::getWonTokens).reversed())
                .toList();
        return userSortedByToken.stream()
                .map(user -> UserRanking.builder()
                        .username(user.getUsername())
                        .rank(userSortedByToken.indexOf(user) + 1)
                        .build())
                .collect(Collectors.toList());
    }
}
