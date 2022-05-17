package com.thxbrop.oss.servlet;

import com.thxbrop.oss.DBFactory;
import com.thxbrop.oss.Result;
import com.thxbrop.oss.entity.User;
import com.thxbrop.oss.util.ServletUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.thxbrop.oss.DBFactory.autoClosed;

@WebServlet({"/user", "/user/*"})
public class UserServlet extends HttpServlet {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    /**
     * Register
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        String username = req.getParameter(USERNAME);
        autoClosed(DBFactory.getUserController(), c -> {
            Result<User> result = c.register(email, username, password);
            ServletUtil.parseResult(resp, result);
        });
    }

    /**
     * Login
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        autoClosed(DBFactory.getUserController(), c -> {
            Result<User> result = c.login(email, password);
            ServletUtil.parseResult(resp, result);
        });
    }

    /**
     * Find
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter(EMAIL);
        autoClosed(DBFactory.getUserController(), c -> {
            Result<User> result = c.getByEmail(email);
            ServletUtil.parseResult(resp, result);
        });
    }
}
