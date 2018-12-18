package org.payment.demo;

import agregator.CheckUser;
import agregator.GetUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserService {
    private static final Map<String, User> users = new HashMap<>();
    private static int id = 0;

    public User registerUser(GetUser getUser) {
        if (getUser.getLogin().isEmpty() || users.get(getUser.getLogin()) != null)
            return null;
        if (getUser.getPasswotd().length() < 6)
            return null;
        if (!getUser.getFirstname().matches("[a-zA-Zа-яА-Я]+") || !getUser.getSecondname().matches("[a-zA-Zа-яА-Я]+"))
            return null;

        User user = new User(getUser);
        id++;
        user.setId(id);
        users.put(getUser.getLogin(), user);
        return user;
    }

    public boolean loginUser(CheckUser checkUser) {
        User user = users.get(checkUser.getLogin());
        if (user != null) {
            return user.getPassword().equals(checkUser.getUserPassword());
        }
        return false;
    }

    public int countUsers() {
        return users.size();
    }

    public User getUserByLogin(String login) {
        return users.get(login);
    }

}
