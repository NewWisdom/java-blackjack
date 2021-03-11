package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {
    protected final Cards cards;
    protected final Name name;
    protected State state;

    public User(String name) {
        this(new Name(name));
    }

    public User(Name name) {
        this(new ArrayList<>(), name);
    }

    public User(List<Card> cards, String name) {
        this(new Cards(cards), new Name(name));
    }

    public User(List<Card> cards, Name name) {
        this(new Cards(cards), name);
    }

    public User(Cards cards, Name name) {
        this.cards = cards;
        this.name = name;
    }

    public final void receiveCards(Cards anotherCards) {
        anotherCards.getCards()
                .forEach(cards::add);
        state = StateFactory.generateStateByCards(cards);
    }

    public final boolean isAbleToHit() {
        return !cards.isBust();
    }

    public final Score score() {
        return cards.totalScore();
    }

    public final Cards getCards() {
        return cards;
    }

    public final String getName() {
        return name.getName();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public final String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(cards, user.cards) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, name);
    }
}
