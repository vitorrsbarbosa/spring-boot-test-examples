package com.barbosa.order;

import com.barbosa.order.data.OrderRequest;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import javax.money.Monetary;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class OrderRequestTest {
    @Autowired
    private JacksonTester<OrderRequest> jacksonTester;

    @Test
    @DisplayName("Deserialize correctly")
    void deserializeFromCorrectFormat() throws IOException {
        var json = "{\"amount\": \"USD 50,00\"}";
        var expectedAmount = Money.of(50, Monetary.getCurrency("USD"));

        var orderRequest = jacksonTester.parseObject(json);

        assertThat(orderRequest.getAmount()).isEqualTo(expectedAmount);
    }

    @Test
    @DisplayName("Deserialize from Json via path")
    void deserializeFromJson() throws IOException {
        var expectedAmount = Money.of(100, Monetary.getCurrency("EUR"));

        var orderRequest = jacksonTester.readObject("order.json");

        assertThat(orderRequest.getAmount()).isEqualTo(expectedAmount);
    }
}
