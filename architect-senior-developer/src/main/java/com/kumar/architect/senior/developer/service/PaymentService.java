package com.kumar.architect.senior.developer.service;
import com.kumar.architect.senior.developer.entity.Payment;
import com.kumar.architect.senior.developer.entity.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(String userId, double amount) {
        // Additional payment processing logic can be added here
        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setAmount(amount);
        // Save the payment details
        return paymentRepository.save(payment);
    }

    // Hystrix fallback method in case of payment processing failure
    public Payment fallbackPayment(String userId, double amount) {
        // Provide a fallback response or take alternative action
        System.out.println("Payment processing failed. Using fallback method.");
        return null;
    }
}

