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
    private static final String ONLY_PASSWORD = "onlyPassword";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter(EMAIL);
        boolean onlyPassword = req.getParameter(ONLY_PASSWORD) != null;
        autoClosed(DBFactory.getUserController(), c -> {
            Result<User> result = c.getByEmail(email);
            if (onlyPassword) {
                ServletUtil.append(resp, result.value.getPassword());
            } else {
                ServletUtil.parseResult(resp, result);
            }
        });
    }

}
