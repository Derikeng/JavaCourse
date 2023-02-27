package com.academy.base;

import com.academy.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserBase {
    private final List<User> stock = new ArrayList<>();

    public User getById(int id) {
        User result = new User();
        result.setId(-1);
        for (User user : stock) {
            if (user.getId() == id) {
                result = user;
            }
        }
        return result;
    }

    public User getUserLoginPassword(final String login, final String password) {
        User result = new User();
        result.setId(-1);
        for (User user : stock) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }
        return result;
    }

    public boolean add(final User user) {
        for (User u : stock) {
            if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(u.getPassword())) {
                return false;
            }
        }
        return stock.add(user);
    }

    public User.ROLE getRoleLoginPassword(final String login, final String password) {
        User.ROLE result = User.ROLE.UNKNOWN;
        for (User user:stock){
            if (user.getLogin().equals(login)&& user.getPassword().equals(password)){
                result=user.getRole();
            }
        }
        return result;
    }

    public boolean userIsExist(final String login, final String password) {
        boolean result = false;
        for (User user : stock) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
