package org.payment.demo;

import agregator.RemitPayment;
import agregator.RemitPaymentResponse;
import hello.wsdl.TransactionRequest;
import hello.wsdl.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.client.core.SoapActionCallback;


@Endpoint
public class PaymentEndpoint {
    private static final String NAMESPACE_URI = "urn:Agregator";

    @Autowired
    private PaymentValidator paymentValidator;

    @Autowired
    private UserService userService;

    @Autowired
    TransactionService transactionService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RemitPayment")
    @ResponsePayload
    public RemitPaymentResponse validatePayment(@RequestPayload RemitPayment request) {
        RemitPaymentResponse response = new RemitPaymentResponse();
        if (!paymentValidator.isValidPayment(request) || userService.countUsers() < request.getUserId()) {
            response.setRemitPaymentResult(-1);
        } else {
            TransactionRequest transactionRequest = new TransactionRequest();
            transactionRequest.setCardNumber(request.getCreditCardInfo().getCardNumber());
            transactionRequest.setCardOwner(request.getCreditCardInfo().getCardholdersName());
            transactionRequest.setCardValidity(new Double(request.getCreditCardInfo().getValidityMM()).intValue() + "/" + new Double(request.getCreditCardInfo().getValidityYY()).intValue());
            transactionRequest.setCvvCode(new Double(request.getCreditCardInfo().getCVC()).shortValue());
            transactionRequest.setTransactionAmount(request.getOrderCoast());
            TransactionResponse transactionResponse = transactionService.processTransaction(transactionRequest);

            if (transactionResponse.getStatus().equals("Success"))
                response.setRemitPaymentResult(0);
            else if (transactionResponse.getStatus().equals("Invalid Card Number"))
                response.setRemitPaymentResult(-1);
            else if (transactionResponse.getStatus().equals("Invalid Card Validity"))
                response.setRemitPaymentResult(-2);
            else  if (transactionResponse.getStatus().equals("Invalid Card Data"))
                response.setRemitPaymentResult(-3);
            else if (transactionResponse.getStatus().equals("Insufficient Funds"))
                response.setRemitPaymentResult(-4);
            else response.setRemitPaymentResult(-5);
        }

        return response;
    }
}
