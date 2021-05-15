package com.barbosa.receipt;

import com.barbosa.receipt.data.ReceiptResponse;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import javax.money.Monetary;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ReceiptResponseTest {
    @Autowired
    private JacksonTester<ReceiptResponse> jacksonTester;

    @Test
    void serializeInCorrectFormat() throws IOException {
        ReceiptResponse receipt = new ReceiptResponse(
                LocalDateTime.of(2021, 5, 9, 16, 0),
                "4532756279624064",
                Money.of(50, Monetary.getCurrency("USD")));

        JsonContent<ReceiptResponse> json = jacksonTester.write(receipt);

        assertThat(json).extractingJsonPathStringValue("$.date").isEqualTo("09.05.2021 16:00");
        assertThat(json).extractingJsonPathStringValue("$.amount").isEqualTo("USD 50,00");
    }

    @Test
    void serializeCorrectJson() throws IOException {
        ReceiptResponse receipt = new ReceiptResponse(
                LocalDateTime.of(2021, 3, 30, 17, 5),
                "4532756279624064",
                Money.of(100.0, Monetary.getCurrency("EUR")));

        JsonContent<ReceiptResponse> json = jacksonTester.write(receipt);

        assertThat(json).isEqualToJson("receipt.json");
    }
}