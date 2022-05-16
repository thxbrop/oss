package com.thxbrop.oss.servlet;

import com.google.gson.Gson;
import com.thxbrop.oss.DBFactory;
import com.thxbrop.oss.controller.CommodityController;
import com.thxbrop.oss.entity.Commodity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = {"/commodity", "/commodity/*"})
public class CommodityServlet extends HttpServlet {
    private static final String LIMIT = "limit";
    private static final String ID = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        CommodityController controller = DBFactory.getCommodityController();
        resp.setContentType("application/json");
        if (uri.endsWith("/commodity")) {
            int limit = Integer.parseInt(req.getParameter(LIMIT));
            List<Commodity> commodities = controller.findAll(limit);
            String json = new Gson().toJson(commodities);
            resp.getWriter().append(json);
        } else {
            int id = Integer.parseInt(req.getParameter(ID));
            Commodity commodity = controller.findById(id);
            String json = new Gson().toJson(commodity);
            resp.getWriter().append(json);
        }
        controller.close();
    }
}
