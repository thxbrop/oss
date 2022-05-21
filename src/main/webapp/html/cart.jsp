<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.thxbrop.oss.Contracts" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cart</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/<%=Contracts.ICON%>">
        <script src="<%=Contracts.JQUERY_JS%>"></script>
        <script src="<%=Contracts.COOKIE_JS%>"></script>
        <link href="<%=Contracts.BOOTSTRAP_CSS%>" rel="stylesheet">
        <script src="<%=Contracts.BOOTSTRAP_JS%>"></script>
        <link rel="stylesheet" href="../css/text.css">
        <script src="../js/index.js"></script>
    </head>
    <body class="bg-dark user-select-none">
        <header class="p-3 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">

                    </a>

                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="../index.jsp" class="nav-link px-2 text-white">主页</a></li>
                        <li><a href="#" class="nav-link px-2 text-white">分类</a></li>
                        <li><a href="order.jsp" class="nav-link px-2 text-white">订单</a></li>
                        <li><a href="https://github.com/thxbrop/oss" class="nav-link px-2 text-white">
                            获取最新源码
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-github" viewBox="0 0 16 16">
                                <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path>
                            </svg>
                        </a></li>
                    </ul>

                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                        <input type="search" class="form-control form-control-dark" placeholder="Search..."
                               aria-label="Search">
                    </form>

                    <div class="text-end" id="toolbar-btn-group">
                        <a class="btn btn-danger me-2" type="button" id="btn-logout">退出登录</a>
                    </div>
                </div>
            </div>
        </header>

        <div class="container py-5">
            <table class="table table-light table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">商品名</th>
                    <th scope="col">预览图片</th>
                    <th scope="col">价格</th>
                </tr>
                </thead>
                <tbody id="table-cart">

                </tbody>
            </table>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button type="button" class="btn btn-primary me-md-2" id="cart-settle">结算
                </button>
                <button type="button" class="btn btn-danger" id="cart-clear">清空</button>
            </div>
        </div>

    </body>
    <script>
        function initTable() {
            const table = $("#table-cart")
            const jsonDTO = Cookies.get("cart")
            const json = jsonDTO == null ? "[]" : jsonDTO
            const list = $.parseJSON(json)
            $.each(list, function (i, each) {
                const tr = document.createElement('tr')
                const th = document.createElement('th')
                th.scope = "row"
                th.innerText = each.id
                const td_name = document.createElement('td')
                const td_image = document.createElement('td')
                const td_price = document.createElement('td')
                td_name.innerText = each.name
                td_image.innerHTML = "<img src='" + each.img + "' alt='" + each.name + "' height='64' width='64'>"
                td_price.innerText = each.price
                tr.append(th)
                tr.append(td_name)
                tr.append(td_image)
                tr.append(td_price)
                table.append(tr)
            })
        }

        function logout() {
            Cookies.remove("email")
            Cookies.remove("password")
            location.href = "${pageContext.request.contextPath}/index.jsp"
        }

        initTable()

        $('#btn-logout').click(function () {
            logout()
        })
        $('#cart-settle').click(function () {
            $.get("${pageContext.request.contextPath}/user?email=" + Cookies.get("email"), function (data) {
                if (data.status === 'success') {
                    const ids = JSON.parse(Cookies.get("cart")).map(getId)
                    let url = "${pageContext.request.contextPath}/order/commit?userId=" + data.value.id
                    for (let i = 0; i < ids.length; i++) {
                        url += "&commodities=" + ids[i]
                    }
                    $.ajax({
                        url: url,
                        dataType: "json",
                        success: function (d) {
                            if (d.status === 'success') {
                                Cookies.remove("cart")
                                location.reload()
                            }
                            alert(d.status)
                        }
                    })
                } else {
                    return data.status
                }
            })
        })

        $('#cart-clear').click(function () {
            Cookies.remove("cart")
            location.reload()
        })

        function getId(c) {
            return c.id
        }
    </script>
</html>
