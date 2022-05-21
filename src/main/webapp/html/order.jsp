<%@ page import="com.thxbrop.oss.Contracts" %><%--
  Created by IntelliJ IDEA.
  User: qq277
  Date: 2022/5/21
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Order</title>
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
                        <li><a href="category.jsp" class="nav-link px-2 text-white">分类</a></li>
                        <li><a href="#" class="nav-link px-2 text-secondary">订单</a></li>
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
                    <th scope="col">订单编号</th>
                    <th scope="col">订单生成时间</th>
                </tr>
                </thead>
                <tbody id="table-cart">

                </tbody>
            </table>
        </div>
    </body>
    <script>
        $.get("${pageContext.request.contextPath}/user?email=" + Cookies.get("email"), function (data) {
            if (data.status === 'success') {
                $.ajax({
                    url: "${pageContext.request.contextPath}/order/find",
                    dataType: "json",
                    data: {
                        "userId": data.value.id
                    },
                    success: function (d) {
                        if (d.status === "success") {
                            const list = d.value
                            for (let i = 0; i < list.length; i++) {
                                const order = list[i]
                                const tr = document.createElement('tr')
                                const th_1 = document.createElement('th')
                                const th_2 = document.createElement('th')
                                th_1.innerText = order.id
                                th_2.innerText = order.createdAt
                                tr.append(th_1)
                                tr.append(th_2)
                                $("#table-cart").append(tr)
                            }
                        }

                    }
                })
            }
        })

    </script>
</html>
