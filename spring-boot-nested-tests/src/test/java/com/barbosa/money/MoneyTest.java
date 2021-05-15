package com.barbosa.money;

import com.barbosa.currency.exception.CurrencyMismatchException;
import com.barbosa.currency.unit.CurrencyUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Nested
    @DisplayName("Equality is based on values")
    class Equality {
        @Test
        @DisplayName("monies with same amounts are equal")
        void moniesWithSameAmountsAreEqual() {
            var eur = CurrencyUnit.of("EUR");
            var first = Money.of(eur, 3.99);
            var second = Money.of(eur, 3.99);
            assertThat(first).isEqualTo(second);
        }
        @Test
        @DisplayName("monies with different amounts are not equal")
        void moniesWithDifferentAmountsAreNotEqual() {
            CurrencyUnit eur = CurrencyUnit.of("EUR");
            Money first = Money.of(eur, 3.99);
            Money second = Money.of(eur, 3.89);

            assertThat(first).isNotEqualTo(second);
        }

        @Test
        @DisplayName("monies with different currencies are not equal")
        void moniesWithDifferentCurrenciesAreNotEqual() {
            CurrencyUnit eur = CurrencyUnit.of("EUR");
            CurrencyUnit usd = CurrencyUnit.of("USD");
            Money first = Money.of(eur, 3.99);
            Money second = Money.of(usd, 3.99);

            assertThat(first).isNotEqualTo(second);
        }
    }

    @Nested
    @DisplayName("adding monetary amounts")
    class Addition {
        @Test
        @DisplayName("can add monies of same currency")
        void addMoneyWithSameCurrency() {
            CurrencyUnit eur = CurrencyUnit.of("EUR");
            Money money = Money.of(eur, 4.25);
            Money addend = Money.of(eur, 1.4);

            Money sum = money.add(addend);

            assertThat(sum.getAmount()).isEqualTo(BigDecimal.valueOf(5.65));
        }

        @Test
        @DisplayName("cannot add monies of different currency")
        void addMoneyWithDifferentCurrency() {
            CurrencyUnit eur = CurrencyUnit.of("EUR");
            CurrencyUnit usd = CurrencyUnit.of("USD");
            Money money = Money.of(eur, 4.25);
            Money addend = Money.of(usd, 1.4);

            assertThrows(CurrencyMismatchException.class, () -> money.add(addend));
        }
    }
}
