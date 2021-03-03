package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private final List<User> users;

    public Users(List<String> names) {
        this.users = names.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    public List<User> users() {
        return Collections.unmodifiableList(users);
    }

    public void distributeToEachUser() {
        users.forEach(user -> user.distribute(Deck.popTwo()));
    }

    public List<List<Card>> showCardsByUsers() {
        return users.stream()
                .map(User::show)
                .collect(Collectors.toList());
    }

    public List<String> showNames() {
        return Collections.unmodifiableList(users.stream()
                .map(User::getName)
                .collect(Collectors.toList()));
    }
}
