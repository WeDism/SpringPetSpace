<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/>
    <link rel="stylesheet" href="<c:url value="/web_resources/css/custom/login.css"/>">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <h2 class="form-sign-in-heading"><a href="<c:url value="/login"/>">Pet network</a></h2>
            <div class="row">
                <div class="col-3" style="padding: 1rem">
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <a class="nav-link active" id="v-pills-sign-in-tab" data-toggle="pill" href="#v-pills-sign-in" role="tab"
                           aria-controls="v-pills-sign-in" aria-selected="true">Sign in</a>
                        <a class="nav-link" id="v-pills-register-tab" data-toggle="pill" href="#v-pills-register" role="tab"
                           aria-controls="v-pills-register" aria-selected="false">Register</a>
                    </div>
                </div>
                <div class="col-9" style="padding: 1rem">
                    <div class="tab-content" id="v-pills-tabContent">
                        <div class="tab-pane fade show active" id="v-pills-sign-in" role="tabpanel" aria-labelledby="v-pills-sign-in-tab">
                            <form id="loginForm" class="form-sign-in" action="<c:url value="/login"/>" method="post">
                                <input class="form-control" type="text" name="nickname" placeholder="Nickname" required autofocus>
                                <input class="form-control" type="password" name="password" placeholder="Password" required>
                                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign in">
                            </form>
                        </div>
                        <div class="tab-pane fade" id="v-pills-register" role="tabpanel" aria-labelledby="v-pills-register-tab">
                            <form id="registerForm" class="form-register" action="<c:url value="/register"/>" method="post">
                                <input class="form-control" type="email" name="email" placeholder="Email" required>
                                <input class="form-control" type="text" name="nickname" placeholder="Nickname" required autofocus>
                                <input class="form-control" type="text" name="name" placeholder="Name" required>
                                <input class="form-control" type="text" name="surname" placeholder="Surname" required>
                                <input class="form-control" type="text" name="patronymic" placeholder="Patronymic">
                                <input class="form-control" type="datetime-local" name="birthday" placeholder="Birthday">
                                <input class="form-control" type="text" size="15" name="phone" placeholder="Phone">
                                <input class="form-control" type="password" name="password" placeholder="Password" required>
                                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Register">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>