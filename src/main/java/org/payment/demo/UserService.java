package org.payment.demo;

import agregator.CheckUser;
import agregator.GetUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserService {
    private ArrayList<User> users = new ArrayList<>();

    public boolean registerUser(GetUser getUser){
        if (getUser.getLogin().isEmpty()) {
            return false;
        } else {
            users.add(new User(getUser));
            return true;
        }
    }

    public boolean loginUser(CheckUser checkUser){
        //Fixme
        return true;
    }

}
