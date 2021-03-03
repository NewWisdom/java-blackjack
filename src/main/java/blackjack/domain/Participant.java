package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    protected Cards cards = new Cards(new ArrayList<>());

    public Participant() {
    }

    public void distribute(Cards cards) {
        this.cards = cards;
    }

    public abstract boolean isDrawable();

    public void draw(){
        cards.combine(Deck.popOne());
    }

    public abstract List<Card> show();
}
