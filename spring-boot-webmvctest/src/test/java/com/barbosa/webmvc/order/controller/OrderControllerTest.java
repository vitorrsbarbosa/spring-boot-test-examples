package com.barbosa.webmvc.order.controller;

import com.barbosa.webmvc.order.entity.Order;
import com.barbosa.webmvc.order.exception.OrderAlreadyPaid;
import com.barbosa.webmvc.order.service.OrderService;
import com.barbosa.webmvc.payment.entity.Payment;
import com.barbosa.webmvc.receipt.entity.Receipt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    public static final String CREDIT_CARD_NUMBER = "12341234";
    public static final String POST_PAY = "/order/{id}/payment";
    public static final String GET_RECEIPT = "/order/{id}/receipt";
    public static final double AMOUNT = 100.0;
    public static final long ORDER_ID = 1L;
    public static final long PAYMENT_ID = 2L;
    @MockBean
    private OrderService orderService;
    @Autowired
    private MockMvc mockMvc;

//  Verify HTTP Request Mapping And Deserialization
    @Test
    void payOrder() throws Exception {
        var order = new Order(ORDER_ID, LocalDateTime.now(), 100.0, false);
        var payment = new Payment(PAYMENT_ID, order, CREDIT_CARD_NUMBER);

        when(orderService.pay(eq(ORDER_ID), eq(CREDIT_CARD_NUMBER))).thenReturn(payment);

        mockMvc.perform(post(POST_PAY, order.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"creditCardNumber\": \"12341234\"}"))
                .andExpect(status().isCreated());
    }

//  Verify Field Validation
    @Test
    void paymentFailsWhenCreditCardNumberNotGiven() throws Exception {
        var order = new Order(ORDER_ID, LocalDateTime.now(), 100.0, false);
        var payment = new Payment(PAYMENT_ID, order, CREDIT_CARD_NUMBER);

        when(orderService.pay(any(), any())).thenReturn(payment);

        mockMvc.perform(post(POST_PAY, order.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

//  Verify Result Serialization
    @Test
    void getReceiptForOrder() throws Exception {
        var receipt = new Receipt(
                LocalDateTime.now(),
                CREDIT_CARD_NUMBER,
                AMOUNT);

        when(orderService.getReceipt(eq(ORDER_ID))).thenReturn(receipt);

        mockMvc.perform(get(GET_RECEIPT, ORDER_ID))
                .andExpect(jsonPath("$.date").isNotEmpty())
                .andExpect(jsonPath("$.creditCardNumber").value(CREDIT_CARD_NUMBER))
                .andExpect(jsonPath("$.amount").value(AMOUNT));
    }

//  Verify Error Handling
    @Test
    void cannotPayAlreadyPaidOrder() throws Exception {
        when(orderService.pay(eq(ORDER_ID), any())).thenThrow(OrderAlreadyPaid.class);

        mockMvc.perform(post("/order/{id}/payment", ORDER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"creditCardNumber\": \"4532756279624064\"}"))
                .andExpect(status().isMethodNotAllowed());
    }

}