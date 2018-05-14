<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/>
    <link rel="stylesheet" href="<c:url value="/web_resources/css/custom/login.css"/>">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <h2 class="form-sign-in-heading"><a href="<c:url value="/login"/>"><span class="oi oi-bug" title="Pet network" aria-hidden="true"></span>
                Pet network</a></h2>
            <div class="row">
                <div class="col-3 left-col">
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <a class="nav-link active" id="v-pills-sign-in-tab" data-toggle="pill" href="#v-pills-sign-in" role="tab"
                           aria-controls="v-pills-sign-in" aria-selected="true"><span class="oi oi-account-login" title="sign-in" aria-hidden="true"></span>&nbsp;
                            Sign in</a>
                        <a class="nav-link" id="v-pills-sign-up-tab" data-toggle="pill" href="#v-pills-sign-up" role="tab"
                           aria-controls="v-pills-sign-up" aria-selected="false"><span class="oi oi-pencil" title="sign-up" aria-hidden="true"></span>&nbsp;
                            Sign up</a>
                    </div>
                </div>
                <div class="col-9 right-col">
                    <div class="tab-content" id="v-pills-tabContent">
                        <div class="tab-pane fade show active" id="v-pills-sign-in" role="tabpanel" aria-labelledby="v-pills-sign-in-tab">
                            <form id="loginForm" class="form-sign-in" action="<c:url value="/login"/>" method="post">
                                <input class="form-control" type="text" name="nickname" placeholder="Nickname" required autofocus>
                                <input class="form-control" type="password" name="password" placeholder="Password" required>
                                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign in">
                            </form>
                        </div>
                        <div class="tab-pane fade" id="v-pills-sign-up" role="tabpanel" aria-labelledby="v-pills-sign-up-tab">
                            <form id="registerForm" class="form-sign-up" action="<c:url value="/sign_up"/>" method="post">
                                <input class="form-control" type="email" name="email" placeholder="Email" required>
                                <input class="form-control" type="text" name="nickname" placeholder="Nickname" required>
                                <input class="form-control" type="text" name="name" placeholder="Name" required>
                                <input class="form-control" type="text" name="surname" placeholder="Surname" required>
                                <input class="form-control" type="text" name="patronymic" placeholder="Patronymic">
                                <input class="form-control" type="text" id="datetimepicker" name="birthday" placeholder="Birthday">
                                <input class="form-control" type="text" size="15" name="phone" placeholder="Phone">
                                <input class="form-control" type="password" name="password" placeholder="Password" required>
                                <div class="form-group form-sign-up">
                                    <div class="row form-sign-up">
                                        <div class="col-6 custom-control custom-radio">
                                            <input type="radio" id="customRadio1" name="role.roleEssenceEnum" class="custom-control-input" value="USER"
                                                   checked="checked"/>
                                            <label class="custom-control-label" for="customRadio1">&nbsp;USER</label>
                                        </div>
                                        <div class="col-6 custom-control custom-radio">
                                            <input type="radio" id="customRadio2" name="role.roleEssenceEnum" class="custom-control-input" value="ADMIN">
                                            <label class="custom-control-label" for="customRadio2">&nbsp;ADMIN</label>
                                        </div>
                                    </div>
                                </div>
                                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Register">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $.datetimepicker.setDateFormatter('moment');
        $('#datetimepicker').datetimepicker({
            format: 'YYYY-MM-DDTHH:mm'
        });
        <c:choose>
        <c:when test="${not empty requestScope.stateRegistration and requestScope.stateRegistration.booleanValue()}">
        $.notify({
            title: '<strong>Complete!</strong>',
            message: 'You are success created login'
        }, {
            type: 'success',
            delay: 15000
        });
        </c:when>
        <c:when test="${not empty requestScope.stateRegistration and not requestScope.stateRegistration.booleanValue()}">
        $.notify({
            title: '<strong>Error!</strong>',
            message: 'You aren\'t created login'
        }, {
            type: 'danger',
            delay: 0
        });
        </c:when>
        </c:choose>
        <c:forEach items="${requestScope.errors}" var="error" varStatus="status">
        $.notify({
            title: '<strong>${error.left}</strong>',
            message: '${error.right}'
        }, {
            type: 'danger',
            delay: 0
        });
        </c:forEach>
    </script>
</div>
</body>
</html>