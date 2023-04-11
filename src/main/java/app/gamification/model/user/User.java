package app.gamification.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
    private final String username;
    private final String password;
    private boolean isLoggedIn;
    private int wonTokens;

}
