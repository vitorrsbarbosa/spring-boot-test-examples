package com.webmvc.order.controller;

import com.webmvc.order.exception.OrderAlreadyPaid;
import com.webmvc.order.service.OrderService;
import com.webmvc.payment.data.PaymentRequest;
import com.webmvc.payment.data.PaymentResponse;
import com.webmvc.receipt.entity.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/{id}/payment")
    public ResponseEntity<PaymentResponse> pay(
            @PathVariable("id") Long orderId,
            @RequestBody @Valid PaymentRequest paymentRequest,
            UriComponentsBuilder uriComponentsBuilder) {
        var payment = orderService.pay(orderId, paymentRequest.getCreditCardNumber());
        var location = uriComponentsBuilder.path("order/{id}/receipt").buildAndExpand(orderId).toUri();
        var response = new PaymentResponse(payment.getOrder().getId(), payment.getCreditCardNumber());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/order/{id}/receipt")
    public ResponseEntity<Receipt> getReceipt(@PathVariable("id") Long orderId) {
        var receitp = orderService.getReceipt(orderId);
        return ResponseEntity.ok().body(receitp);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public String handleOrderAlreadyPaid(OrderAlreadyPaid orderAlreadyPaid) {
        return orderAlreadyPaid.getMessage();
    }
}
