package com.thxbrop.oss.servlet.order;

import com.thxbrop.oss.DBFactory;
import com.thxbrop.oss.Result;
import com.thxbrop.oss.entity.Order;
import com.thxbrop.oss.util.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/order/commit")
public class CommitServlet extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final String COMMODITIES = "commodities";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter(USER_ID));
        String[] commodities = req.getParameterValues(COMMODITIES);
        List<Integer> c = Arrays.stream(commodities).map(Integer::parseInt).collect(Collectors.toList());
        DBFactory.autoClosed(DBFactory.getOrderRepository(), orderRepository -> {
            try {
                Result<Order> result = orderRepository.commit(userId, c);
                ServletUtil.parseResult(resp, result);
            } catch (Exception e) {
                ServletUtil.append(resp, e.getMessage());
            }
        });
    }
}
