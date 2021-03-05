package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @DisplayName("결과를 뒤집는다. (ex. 승을 패로, 패를 승으로)")
    @Test
    public void reverse() {
        Result result = Result.WIN;

        assertThat(result.reverse(result)).isEqualTo(Result.LOSE);
    }

    @DisplayName("각 객체는 리스트 안에 자신이 몇 개가 있는지 계산한다.")
    @Test
    public void count() {
        Result win = Result.WIN;
        List<Result> results = Arrays.asList(Result.WIN, Result.WIN, Result.STAND_OFF, Result.LOSE);

        assertThat(win.count(results)).isEqualTo(2);
    }
}
