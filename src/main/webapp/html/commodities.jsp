<%@ page import="com.thxbrop.oss.Contracts" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Commodities</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/svg+xml" href="<%=Contracts.ICON%>">
        <script src="<%=Contracts.JQUERY_JS%>"></script>
        <script src="<%=Contracts.COOKIE_JS%>"></script>
        <link href="<%=Contracts.BOOTSTRAP_CSS%>" rel="stylesheet">
        <script src="<%=Contracts.BOOTSTRAP_JS%>"></script>
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
