package blackjack.domain.user;

public class Money {
    private static final int MIN_MONEY = 0;
    private final long value;

    public Money(long value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(long value) {
        if (value < MIN_MONEY) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    public Money sum(Money anotherMoney) {
        return new Money(value + anotherMoney.value);
    }

    public long calculateProfit(double rate) {
        return (long) (value * rate);
    }

    public long getValue() {
        return value;
    }

    public long getProfit(Money anotherMoney) {
        return value - anotherMoney.value;
    }
}
