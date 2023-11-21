package com.kumar.architect.senior.developer.controller;
import com.kumar.architect.senior.developer.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.HystrixCommand;
import org.springframework.cloud.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody Map<String, Object> paymentDetails) {
        String userId = (String) paymentDetails.get("userId");
        double amount = (double) paymentDetails.get("amount");

        // Use Hystrix to wrap the payment processing logic
        HystrixCommand<String> paymentCommand = new HystrixCommand<String>(
                HystrixCommandGroupKey.Factory.asKey("PaymentService"),
                HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("PaymentService"))
                        .fallbackMethod("fallbackPayment"),
                () -> {
                    // Actual payment processing logic
                    paymentService.processPayment(userId, amount);
                    return "Payment processed successfully.";
                }
        ) {};

        String result = paymentCommand.execute();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

