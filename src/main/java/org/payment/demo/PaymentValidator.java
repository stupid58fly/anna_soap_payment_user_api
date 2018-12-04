package org.payment.demo;

import agregator.RemitPayment;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {
    public boolean isValidPayment(RemitPayment paymentInfo){
        if(paymentInfo.getUserId()>0) return true;
        else return false;
    }
}
