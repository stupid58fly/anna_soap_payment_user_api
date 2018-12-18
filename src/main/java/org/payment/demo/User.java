package org.payment.demo;

import agregator.GetUser;

public class User {
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private int id;

    public User(GetUser getUser){
        firstName = getUser.getFirstname();
        secondName = getUser.getSecondname();
        login = getUser.getLogin();
        password = getUser.getPasswotd();
    }

    public User(String firstName, String secondName, String login, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
