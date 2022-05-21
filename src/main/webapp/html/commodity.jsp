<%@ page import="com.thxbrop.oss.Contracts" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
    <head>
        <title>商品详情</title>
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
                        <li><a href="#" class="nav-link px-2 text-white">订单</a></li>
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
                        <!-- ETC. -->
                    </div>
                </div>
            </div>
        </header>
        <div class="container py-5">
            <div class="row row-cols-1 row-cols-md-2 g-3 light" id="commodity-detail">

            </div>
        </div>

        <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
            <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header">
                    <strong class="me-auto" id="toast-from">系统消息</strong>
                    <small id="toast-time">Just Now</small>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body" id="toast-body">
                    Hello, world! This is a toast message.
                </div>
            </div>
        </div>
    </body>

    <script>
        function getCommodityById(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/commodity",
                method: "get",
                dataType: "json",
                data: {
                    "id": id
                },
                success: function (data) {
                    if (data.status === "success") {
                        const value = data.value
                        const cd = $("#commodity-detail")
                        const thumb = document.createElement('img')
                        thumb.src = value.img
                        thumb.alt = value.name
                        thumb.className = "img-thumbnail"
                        cd.append(thumb)
                        const detail = document.createElement("div")
                        detail.className = "col px-4"
                        const title = document.createElement("h1")
                        title.innerText = value.name
                        title.className = "fs-1 text-light"
                        detail.append(title)
                        for (let i = 0; i < value.tags.length; i++) {
                            const tag = document.createElement('span')
                            tag.className = "fs-3 text text-primary"
                            tag.innerText = "#" + value.tags[i] + "\t"
                            detail.append(tag)
                        }

                        const coin = document.createElement("p")
                        coin.innerText = "$ " + value.price
                        coin.className = "fs-2 text text-light neon"
                        detail.append(coin)

                        const btn_group = document.createElement('div')
                        btn_group.className = "d-grid gap-2 d-md-flex justify-content-md-end"
                        const btnAddToCart = document.createElement('button')
                        btnAddToCart.type = "button"
                        btnAddToCart.className = "btn btn-primary me-md-2"
                        btnAddToCart.innerText = "加入购物车"
                        btnAddToCart.onclick = function () {
                            addToCart(value.id)
                        }
                        const btnBuy = document.createElement('button')
                        btnBuy.type = "button"
                        btnBuy.className = "btn btn-outline-light"
                        btnBuy.innerText = "购买"
                        btnBuy.onclick = function () {
                            makeSingleOrder(value.id)
                        }
                        btn_group.append(btnAddToCart)
                        btn_group.append(btnBuy)
                        detail.append(btn_group)

                        cd.append(detail)
                    } else {
                        $("#nav-link-name").text("商品不存在！")
                    }
                }
            })
        }

        function makeSingleOrder(id) {
            toast("订单已生成")
        }

        function addToCart(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/commodity",
                method: "get",
                dataType: "json",
                data: {
                    "id": id
                },
                success: function (data) {
                    if (data.status === "success") {
                        const value = data.value
                        const cartJson = Cookies.get("cart")
                        const cart = cartJson == null ? "[]" : cartJson
                        const list = $.parseJSON(cart)
                        list.push(value)
                        Cookies.set("cart", JSON.stringify(list))
                        toast("加入购物车成功")
                    }
                }
            })
        }


        const toastLiveExample = document.getElementById('liveToast');

        function toast(message, from = "系统消息", time = "Just Now") {
            document.getElementById("toast-body").innerText = message
            document.getElementById("toast-from").innerText = from
            document.getElementById("toast-time").innerText = time
            new bootstrap.Toast(toastLiveExample).show()
        }

        const email = Cookies.get("email")
        const password = Cookies.get("password")
        const container = $('#toolbar-btn-group')
        const isLogin = checkUser("${pageContext.request.contextPath}", email, password)
        if (email == null || password == null || !isLogin) {
            const btn_register = document.createElement('button')
            const btn_login = document.createElement('button')
            btn_register.id = "register"
            btn_login.id = "login"
            btn_register.type = "button"
            btn_login.type = "button"
            btn_register.innerText = "注册"
            btn_login.innerText = "登录"
            btn_register.className = "btn btn-warning me-2"
            btn_login.className = "btn btn-outline-light me-2"
            btn_register.setAttribute('data-bs-target', '#model-register')
            btn_login.setAttribute('data-bs-target', '#model-login')
            btn_register.setAttribute('data-bs-toggle', 'modal')
            btn_login.setAttribute('data-bs-toggle', 'modal')
            container.append(btn_register)
            container.append(btn_login)
        } else {
            const link = document.createElement('a')
            link.type = "button"
            link.className = "btn btn-outline-light me-2"
            link.href = "${pageContext.request.contextPath}/html/cart.jsp"
            link.innerText = "购物车"
            container.append(link)
        }

        getCommodityById(${param.id})

    </script>


</html>
