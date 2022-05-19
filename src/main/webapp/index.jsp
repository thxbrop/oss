<%@ page import="com.thxbrop.oss.Contracts" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <title>OSS</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/<%=Contracts.ICON%>">
        <script src="<%=Contracts.JQUERY_JS%>"></script>
        <script src="<%=Contracts.COOKIE_JS%>"></script>
        <link href="<%=Contracts.BOOTSTRAP_CSS%>" rel="stylesheet">
        <script src="<%=Contracts.BOOTSTRAP_JS%>"></script>
        <style>
            .myCard {
                transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
            }

            .myCard:hover {
                transform: scale(1.05);
                box-shadow: 0 10px 20px rgba(0, 0, 0, .12), 0 4px 8px rgba(0, 0, 0, .06);
            }

            .wrapper {
                position: relative;
                overflow: hidden;
            }

            .wrapper:after {
                content: '';
                display: block;
                padding-top: 100%;
            }

            .wrapper img {
                width: auto;
                height: 100%;
                max-width: none;
                position: absolute;
                left: 50%;
                top: 0;
                transform: translateX(-50%);
            }

        </style>
    </head>

    <body>
        <header class="p-3 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">

                    </a>

                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="#" class="nav-link px-2 text-secondary">主页</a></li>
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

                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search"
                          action="${pageContext.request.contextPath}/commodity/search">
                        <input type="search" class="form-control form-control-dark" placeholder="Search..."
                               aria-label="Search" name="search">
                    </form>

                    <div class="text-end" id="toolbar-btn-group">
                        <!-- ETC. -->
                    </div>
                </div>
            </div>
        </header>

        <div class="container py-5">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-lg-5 g-3" id="commodity-recommend">

            </div>
        </div>

        <footer class="bg-light d-flex flex-wrap justify-content-between align-items-center py-3 px-4 my-4 border-top">
            <div class="col-md-4 d-flex align-items-center">
                <a href="#" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">ThxBro:P</a>
                <span class="mb-3 mb-md-0 text-muted">© 2022 Company, Inc</span>
            </div>

            <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
                <li class="ms-3"><a class="text-muted" href="https://github.com/thxbrop">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-github" viewBox="0 0 16 16">
                        <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path>
                    </svg>
                </a></li>
                <li class="ms-3"><a class="text-muted" href="https://t.me/FloatDef">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-telegram" viewBox="0 0 16 16">
                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.287 5.906c-.778.324-2.334.994-4.666 2.01-.378.15-.577.298-.595.442-.03.243.275.339.69.47l.175.055c.408.133.958.288 1.243.294.26.006.549-.1.868-.32 2.179-1.471 3.304-2.214 3.374-2.23.05-.012.12-.026.166.016.047.041.042.12.037.141-.03.129-1.227 1.241-1.846 1.817-.193.18-.33.307-.358.336a8.154 8.154 0 0 1-.188.186c-.38.366-.664.64.015 1.088.327.216.589.393.85.571.284.194.568.387.936.629.093.06.183.125.27.187.331.236.63.448.997.414.214-.02.435-.22.547-.82.265-1.417.786-4.486.906-5.751a1.426 1.426 0 0 0-.013-.315.337.337 0 0 0-.114-.217.526.526 0 0 0-.31-.093c-.3.005-.763.166-2.984 1.09z"></path>
                    </svg>
                </a></li>
            </ul>
        </footer>

        <div class="modal fade" id="model-register" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">注册</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="input-register-email" class="form-label">电子邮箱地址</label>
                            <input class="form-control" type="email" name="email" id="input-register-email"
                                   placeholder="name@example.com">
                        </div>
                        <div class="mb-3">
                            <label for="input-register-username" class="form-label">用户名</label>
                            <input class="form-control" type="text" name="username" id="input-register-username"
                                   placeholder="可选">
                        </div>
                        <div class="mb-3">
                            <label for="input-register-password" class="form-label">密码</label>
                            <input class="form-control" type="password" name="password"
                                   id="input-register-password">
                        </div>
                        <div id="alert-register"></div>
                    </div>
                    <div class="modal-footer btn-group">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="btn-register">提交</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="model-login" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabelLogin">登录</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="input-login-email" class="form-label">电子邮箱地址</label>
                            <input class="form-control" type="email" name="email" id="input-login-email"
                                   placeholder="name@example.com">
                        </div>
                        <div class="mb-3">
                            <label for="input-login-password" class="form-label">密码</label>
                            <input class="form-control" type="password" name="password"
                                   id="input-login-password">
                        </div>
                        <div id="alert-login"></div>
                    </div>
                    <div class="modal-footer btn-group">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="btn-login">提交</button>
                    </div>
                </div>
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
        const alertPlaceholderRegister = document.getElementById('alert-register');
        const alertPlaceholderLogin = document.getElementById('alert-login');

        function alertInRegister(message, type) {
            const wrapper = document.createElement('div');
            wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

            alertPlaceholderRegister.append(wrapper)
        }

        function alertInLogin(message, type) {
            const wrapper = document.createElement('div');
            wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

            alertPlaceholderLogin.append(wrapper)
        }

        $("#btn-register").click(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/user",
                type: "post",
                dataType: "json",
                success: function (data) {
                    alertInRegister(data.username, "success")
                },
                error: function (message) {
                    alertInRegister(message, "warning")
                }
            })
        })

        $("#btn-login").click(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/account",
                type: "get",
                dataType: "json",
                data: {
                    "email": $("#input-login-email").val(),
                    "password": $("#input-login-password").val()
                },
                success: function (data) {
                    if (data.status === 'success') {
                        Cookies.set("email", data.value.email)
                        Cookies.set("password", data.value.password)
                        location.reload()
                    } else {
                        alertInLogin(data.status, "warning")
                    }
                }
            })
        })

        const toastLiveExample = document.getElementById('liveToast');

        function toast(message, from = "系统消息", time = "Just Now") {
            document.getElementById("toast-body").innerText = message
            document.getElementById("toast-from").innerText = from
            document.getElementById("toast-time").innerText = time
            new bootstrap.Toast(toastLiveExample).show()
        }

        async function checkUser(email, password) {
            $.ajax({
                url: "${pageContext.request.contextPath}/user",
                type: "get",
                dataType: "json",
                data: {
                    'email': email,
                    'onlyPassword': 1
                },
                success: function (data) {
                    if (data.status === 'success') {
                        return data.value === password
                    } else {
                        return false
                    }
                }
            })
        }

        function addItem(src, name, commodityId, tags) {
            const wrapper = document.createElement('div')
            wrapper.className = 'card card-1 myCard'
            const image = document.createElement('img')
            image.className = 'card-img-top'
            image.src = src
            image.alt = name
            const imgWrapper = document.createElement('div')
            imgWrapper.className = 'wrapper'
            imgWrapper.append(image)
            wrapper.append(imgWrapper)
            const body = document.createElement('div')
            body.className = 'card-body'
            const p = document.createElement('p')
            p.className = 'card-text fw-bold text-truncate'
            p.innerText = name
            const tagBody = document.createElement('div')
            tagBody.className = "text-truncate"
            for (let i = 0; i < tags.length; i++) {
                const tag = document.createElement('code')
                tag.innerText = '#' + tags[i] + '\t'
                tag.className = "user-select-none"
                tagBody.append(tag)
            }
            body.append(tagBody)
            body.append(p)
            wrapper.append(body)
            const a = document.createElement('a')
            a.href = "${pageContext.request.contextPath}/html/commodity.jsp?id=" + commodityId
            a.className = "btn btn-primary stretched-link"
            a.innerText = "查看详情"
            body.append(a)
            const col = document.createElement('div')
            col.className = "col"
            col.append(wrapper)
            $('#commodity-recommend').append(col)
        }

        const email = Cookies.get("email")
        const password = Cookies.get("password")
        const container = $('#toolbar-btn-group')
        if (!checkUser(email, password)) {
            const btn_register = document.createElement('button')
            const btn_login = document.createElement('button')
            btn_register.id = "register"
            btn_login.id = "login"
            btn_register.type = "button"
            btn_login.type = "button"
            btn_register.innerText = "注册"
            btn_login.innerText = "登录"
            btn_register.className = "btn btn-warning"
            btn_login.className = "btn btn-outline-light me-2"
            btn_register.setAttribute('data-bs-target', '#model-register')
            btn_login.setAttribute('data-bs-target', '#model-login')
            btn_register.setAttribute('data-bs-toggle', 'modal')
            btn_login.setAttribute('data-bs-toggle', 'modal')
            container.append(btn_register)
            container.append(btn_login)
        } else {
            const link = document.createElement('button')
            link.type = "button"
            link.className = "btn btn-outline-light me-2"
            link.href = "${pageContext.request.contextPath}/html/profiles.jsp"
            link.innerText = "购物车"
            container.append(link)
        }

        function getRecommendCommodities() {
            $.ajax({
                url: "${pageContext.request.contextPath}/commodity/recommend",
                type: "get",
                dataType: "json",
                data: {
                    "limit": 10
                },
                success: function (data) {
                    if (data.status === 'success') {
                        for (let i = 0; i < data.value.length; i++) {
                            const v = data.value[i]
                            addItem(v.img, v.name, v.id, v.tags)
                        }
                    } else {

                    }
                }
            })
        }

        getRecommendCommodities()
    </script>
</html>