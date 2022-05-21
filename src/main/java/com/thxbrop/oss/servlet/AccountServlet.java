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

@WebServlet(value = "/account")
public class AccountServlet extends HttpServlet {
    private static final String EMAIL = "email";
    private static final String IS_REGISTER = "isRegister";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String username = request.getParameter(USERNAME);
        boolean isRegister = request.getParameter(IS_REGISTER) != null;
        autoClosed(DBFactory.getUserRepository(), c -> {
            Result<User> result;
            if (!isRegister) {
                result = c.login(email, password);
            } else {
                result = c.register(email, username, password);
            }
            ServletUtil.parseResult(response, result);
        });
    }

}
