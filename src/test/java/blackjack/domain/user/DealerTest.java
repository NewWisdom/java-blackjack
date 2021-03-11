package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("Dealer 객체를 생성한다.")
    @Test
    void createDealer() {
        Dealer dealer = new Dealer();

        assertThat(dealer).isInstanceOf(Dealer.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    void distributeTwoCards() {
        Dealer dealer = new Dealer();

        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        )));
        int cardCount = dealer.cards().getCards().size();

        assertThat(cardCount).isEqualTo(2);
    }

    @DisplayName("카드 합계가 16 이하인지 확인한다. - 카드를 더 받을 수 있다.")
    @Test
    void isDrawableTrue() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.TWO),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        boolean isAbleToHit = dealer.isAbleToHit();

        assertThat(isAbleToHit).isTrue();
    }

    @DisplayName("카드 합계가 17 이상인지 확인한다. - 카드를 더 받을 수 없다.")
    @Test
    void isDrawableFalse() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        boolean isAbleToHit = dealer.isAbleToHit();

        assertThat(isAbleToHit).isFalse();
    }

    @DisplayName("카드 한장은 공개하고 한장은 숨긴다.")
    @Test
    void show() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        assertThat(dealer.showOneCard()).isInstanceOf(Card.class);
    }

    @DisplayName("받은 카드에 따라 상태를 확인한다. - Hit")
    @Test
    void receiveCardsState() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.TWO),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        State state = dealer.getState();

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("받은 카드에 따라 상태를 확인한다. - blackjack")
    @Test
    void receiveCardsState2() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        State state = dealer.getState();

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @DisplayName("상태에 따라 카드를 받는다. - hit")
    @Test
    void draw1() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        dealer.hit(new Card(Suit.SPACE, Denomination.TWO));
        State state = dealer.getState();

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("상태에 따라 카드를 받는다. - bust")
    void draw2() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        dealer.hit(new Card(Suit.HEART, Denomination.JACK));
        State state = dealer.getState();

        assertThat(state).isInstanceOf(Bust.class);
    }
}
