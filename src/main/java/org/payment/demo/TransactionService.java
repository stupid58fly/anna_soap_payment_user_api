package org.payment.demo;

import hello.wsdl.TransactionRequest;
import hello.wsdl.TransactionResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class TransactionService extends WebServiceGatewaySupport {
    public TransactionResponse processTransaction (TransactionRequest request){
        return  (TransactionResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://webapp-linux-181205003304.azurewebsites.net/bank/", request);

    }
}
