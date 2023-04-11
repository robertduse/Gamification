package app.gamification.repository;

import app.gamification.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> applicationUsers = new ArrayList<>();

    public boolean addUser(User user) {
        user.setWonTokens(0);
        return this.applicationUsers.add(user);
    }

    public boolean existsUser(User checkUser) {
        Optional<User> foundedUser = applicationUsers.stream()
                .filter(user -> user.getUsername().equals(checkUser.getUsername()))
                .findFirst();
        if (foundedUser.isPresent()) {
            User user = foundedUser.get();
            user.setLoggedIn(true);
        }
        return foundedUser.isPresent();
    }

    public List<User> getApplicationUsers() {
        return applicationUsers;
    }

    public User isUserLoggedIn(String username) throws Exception {
        Optional<User> optionalUser = this.applicationUsers.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        if (optionalUser.isEmpty()) {
            throw new Exception("There is no user with this username.");
        }
        if (!optionalUser.get().isLoggedIn()) {
            throw new Exception("Please login first.");
        }
        return optionalUser.get();
    }

}
