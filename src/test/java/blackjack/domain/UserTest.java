package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @DisplayName("User 객체를 생성한다.")
    @Test
    public void createUser() {
        User user = new User("amazzi");

        assertThat(user).isInstanceOf(User.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    public void distributeTwoCards() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.NINE),
                new Card(Shape.DIAMOND, Value.JACK)
        )));
        Cards cards = user.cards;

        assertThat(cards.cards().size()).isEqualTo(2);
    }

    @DisplayName("카드 합계가 21 이하인지 확인한다. - 카드를 더 받을 수 있다.")
    @Test
    public void isDrawableTrue() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK)
        )));

        assertThat(user.isDrawable()).isTrue();
    }

    @DisplayName("카드 합계가 21 초과인지 확인한다. - 카드를 더 받을 수 없다.")
    @Test
    public void isDrawableFalse() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.HEART, Value.TWO),
                new Card(Shape.DIAMOND, Value.JACK),
                new Card(Shape.CLOVER, Value.QUEEN)
        )));

        assertThat(user.isDrawable()).isFalse();
    }

    @DisplayName("카드 합계가 16이하인 경우 카드를 한장 추가로 받는다.")
    @Test
    void draw() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        user.draw();
        Cards cards = user.cards;
        assertThat(cards.cards().size()).isEqualTo(3);
    }

    @DisplayName("카드 두장을 공개한다.")
    @Test
    void show() {
        User user = new User("amazzi");
        user.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Cards cards = user.showCards();
        assertThat(cards.cards().size()).isEqualTo(2);
    }
}
