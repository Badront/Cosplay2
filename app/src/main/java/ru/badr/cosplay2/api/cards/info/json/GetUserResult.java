package ru.badr.cosplay2.api.cards.info.json;

import java.io.Serializable;

import ru.badr.cosplay2.api.cards.User;

/**
 * Created by Badr on 24.11.2015.
 */
public class GetUserResult implements Serializable {
    private String message;
    //List friends;
    private User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
