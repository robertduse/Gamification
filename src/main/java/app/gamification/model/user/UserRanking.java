package app.gamification.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserRanking {
    private final int rank;
    private final String username;
}
