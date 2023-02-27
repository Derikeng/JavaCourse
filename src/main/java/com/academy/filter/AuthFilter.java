package com.academy.filter;

import com.academy.base.UserBase;
import com.academy.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;


public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked") final AtomicReference<UserBase> base = (AtomicReference<UserBase>)
                req.getServletContext().getAttribute("base");
        final HttpSession session = req.getSession();
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            final User.ROLE role = (User.ROLE) session.getAttribute("role");
            moveToMenu(req, resp, role);
        } else if (base.get().userIsExist(login, password)) {
            final User.ROLE role = base.get().getRoleLoginPassword(login, password);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, resp, role);

        } else {

            moveToMenu(req, resp, User.ROLE.UNKNOWN);
        }
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse resp, User.ROLE role)
            throws ServletException, IOException {
        if (role.equals(User.ROLE.USER)) {
            req.getRequestDispatcher("/WEB-INF/basis/user.jsp").forward(req, resp);

        } else {
            req.getRequestDispatcher("/WEB-INF/basis/login.jsp").forward(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}

