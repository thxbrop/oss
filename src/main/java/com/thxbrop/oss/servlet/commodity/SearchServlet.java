package com.thxbrop.oss.servlet.commodity;

import com.thxbrop.oss.DBFactory;
import com.thxbrop.oss.entity.Commodity;
import com.thxbrop.oss.util.ServletUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet("/commodity/search")
public class SearchServlet extends HttpServlet {
    private static final String SEARCH = "search";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        DBFactory.autoClosed(DBFactory.getCommodityController(), c -> {
            List<Commodity> commodities = c.search(req.getParameter(SEARCH));
            ServletUtil.append(resp, commodities);
        });
    }
}
