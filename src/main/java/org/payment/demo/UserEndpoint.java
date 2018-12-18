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
        User user = userService.registerUser(request);
        if(user==null)
            response.setGetUserResult(-1);
        else
            response.setGetUserResult(user.getId());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckUser")
    @ResponsePayload
    public CheckUserResponse loginUser(@RequestPayload CheckUser request) {
        CheckUserResponse response = new CheckUserResponse();
        if(userService.loginUser(request)) response.setCheckUserResult(1);
        else response.setCheckUserResult(-1);

        return response;
    }

    //GetUserNameByLoginResponse
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserNameByLogin")
    @ResponsePayload
    public GetUserNameByLoginResponse getUserName(@RequestPayload GetUserNameByLogin request) {
        GetUserNameByLoginResponse response = new GetUserNameByLoginResponse();
        User user = userService.getUserByLogin(request.getLogin());
        if(user!=null){
            response.setFirstname(user.getFirstName());
            response.setSecondname(user.getSecondName());
            response.setUserId(user.getId());
        }
        return response;
    }
}
