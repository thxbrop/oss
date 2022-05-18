package com.thxbrop.oss.servlet.commodity;

import com.thxbrop.oss.DBFactory;
import com.thxbrop.oss.entity.Commodity;
import com.thxbrop.oss.repository.CommodityRepository;
import com.thxbrop.oss.util.ServletUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/commodity/recommend")
public class RecommendServlet extends HttpServlet {
    private static final String LIMIT = "limit";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CommodityRepository controller = DBFactory.getCommodityController();
        resp.setContentType("application/json");
        int limit = Integer.parseInt(req.getParameter(LIMIT));
        List<Commodity> commodities = controller.findAll(limit);
        ServletUtil.append(resp, commodities);
        controller.close();
    }
}
