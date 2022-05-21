package com.thxbrop.oss.servlet.order;

import com.thxbrop.oss.DBFactory;
import com.thxbrop.oss.entity.Order;
import com.thxbrop.oss.util.ServletUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet("/order/find")
public class FindServlet extends HttpServlet {
    private static final String USER_ID = "userId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int userId = Integer.parseInt(req.getParameter(USER_ID));
        DBFactory.autoClosed(DBFactory.getOrderRepository(), orderRepository -> {
            List<Order> list = orderRepository.findByUserId(userId);
            System.out.println(list);
            System.out.println(userId);
            ServletUtil.append(resp, list);
        });
    }
}
