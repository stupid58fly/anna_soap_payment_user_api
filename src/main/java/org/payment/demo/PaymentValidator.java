package org.payment.demo;

import agregator.RemitPayment;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {
    public boolean isValidPayment(RemitPayment paymentInfo){
        if(paymentInfo.getOrderCoast() < 0) return false;
        return paymentInfo.getUserId() > 0;
    }
}
