<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Commodities</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <table id="table-commodities">
            <tr>
                <th>ID</th>
                <th>商品名称</th>
                <th>价格</th>
            </tr>
        </table>
    </body>
    <script>
        function getAllCommodities() {
            $.ajax({
                url: "${pageContext.request.contextPath}/commodity?limit=5",
                type: "get",
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, n) {
                        insertCommodityItem(n.id, n.name, n.price)
                    })
                },
                error: function (message) {
                    alert(message)
                }
            })
        }

        function insertCommodityItem(id, name, price) {
            const tr = document.createElement("tr")
            const td_id = document.createElement("td")
            td_id.innerText = id
            const td_name = document.createElement("td")
            td_name.innerText = name
            const td_price = document.createElement("td")
            td_price.innerText = price
            tr.append(td_id)
            tr.append(td_name)
            tr.append(td_price)
            $("#table-commodities").append(tr)
        }

        getAllCommodities()
    </script>
</html>
