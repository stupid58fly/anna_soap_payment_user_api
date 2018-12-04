package org.payment.demo;

import agregator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;



@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "urn:Agregator";

    @Autowired
    private UserService userService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUser")
    @ResponsePayload
    public GetUserResponse registerUser(@RequestPayload GetUser request) {
        GetUserResponse response = new GetUserResponse();
        if(userService.registerUser(request)) response.setGetUserResult(1);
        else response.setGetUserResult(2);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckUser")
    @ResponsePayload
    public CheckUserResponse loginUser(@RequestPayload CheckUser request) {
        CheckUserResponse response = new CheckUserResponse();
        if(userService.loginUser(request)) response.setCheckUserResult(1);
        else response.setCheckUserResult(2);

        return response;
    }
}
