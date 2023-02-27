package com.academy.listener;

import com.academy.base.UserBase;
import com.academy.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

import static com.academy.model.User.ROLE.USER;

@WebListener
public class SessionListener implements ServletContextListener {
    private AtomicReference<UserBase> base;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        base = new AtomicReference<>(new UserBase());
        base.get().add(new User(1, "Denis", "qwerty", USER));
        base.get().add(new User(2, "Pavel", "12345", USER));
        base.get().add(new User(3, "Olga", "qwerty123", USER));
        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("base", base);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        base = null;
    }
}
