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

@WebServlet("/commodity")
public class GetByIdServlet extends HttpServlet {
    private static final String ID = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CommodityRepository controller = DBFactory.getCommodityController();
        resp.setContentType("application/json");
        int id = Integer.parseInt(req.getParameter(ID));
        Commodity commodity = controller.findById(id);
        ServletUtil.append(resp, commodity);
        controller.close();
    }
}
