<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>线上考试管理系统</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>
        <link rel="icon" type="image/svg+xml" href="https://developer.android.com/images/picto-icons/learn.svg">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>

    <body class="bg-dark">
        <header class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
            <div class="container">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">主页</a>
                    </li>
                </ul>
                <div class="align-content-end btn-group" id="toolbar-btn-group">

                </div>
            </div>
        </header>

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
                        alertInLogin(data.value.username, "success")
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

        toast("欢迎来到线上考试管理系统")

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
            btn_register.className = "btn btn-primary"
            btn_login.className = "btn btn-secondary"
            btn_register.setAttribute('data-bs-target', '#model-register')
            btn_login.setAttribute('data-bs-target', '#model-login')
            btn_register.setAttribute('data-bs-toggle', 'modal')
            btn_login.setAttribute('data-bs-toggle', 'modal')
            container.append(btn_register)
            container.append(btn_login)
        } else {
            const link = document.createElement('a')
            link.className = "nav-link"
            link.href = "${pageContext.request.contextPath}/html/profiles.jsp"
            link.innerText = "个人中心"
            container.append(link)
        }
    </script>
</html>