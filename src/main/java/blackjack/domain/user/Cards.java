package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    public Cards(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean isBlackjack() {
        return calculateTotalValue().isBlackjack();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getOneCard() {
        return cards.get(FIRST_CARD);
    }

    public Score calculateTotalValue() {
        Score totalScore = sum();
        int aceCount = getAceCount();
        for (int i = 0; i < aceCount; i++) {
            totalScore = totalScore.decideScoreByStatus();
        }
        return totalScore;
    }

    private Score sum() {
        return new Score(cards.stream()
                .mapToInt(Card::getScore)
                .sum());
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public boolean isSoftHand() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void combine(Cards otherCards) {
        this.cards.addAll(otherCards.getCards());
    }

    public boolean isBust() {
        return calculateTotalValue().isBust();
    }
}
