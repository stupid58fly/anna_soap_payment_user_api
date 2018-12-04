package org.payment.demo;

import agregator.RemitPayment;
import agregator.RemitPaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;



@Endpoint
public class PaymentEndpoint {
    private static final String NAMESPACE_URI = "urn:Agregator";

    @Autowired
    private PaymentValidator paymentValidator;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RemitPayment")
    @ResponsePayload
    public RemitPaymentResponse validatePayment(@RequestPayload RemitPayment request) {
        RemitPaymentResponse response = new RemitPaymentResponse();
        if(paymentValidator.isValidPayment(request))
            response.setRemitPaymentResult(0);
        else response.setRemitPaymentResult(1);

        return response;
    }
}
