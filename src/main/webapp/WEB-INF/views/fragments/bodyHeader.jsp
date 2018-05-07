<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <nav class="col-2 navbar navbar-expand-lg navbar navbar-dark bg-dark">
        <div class="navbar-brand"><span class="oi oi-bug" title="Pet space" aria-hidden="true"></span>&nbsp;Pet space</div>
    </nav>
    <c:set var="currentUserRole" value="${fn:toLowerCase(requestScope.user.role)}"/>
    <nav class="col-10 navbar navbar-expand-lg navbar-dark bg-primary">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/${currentUserRole}/"/>">
                        <span class="oi oi-home" title="home" aria-hidden="true"></span>&nbsp;
                        Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/${currentUserRole}/messages"/>">
                        <span class="oi oi-chat" title="messages" aria-hidden="true"></span>&nbsp;Messages</a>
                </li>
            </ul>
        </div>
        <a class="navbar-brand float-right" href="<c:url value="/logout"/>">
            <span class="oi oi-account-logout" title="logout" aria-hidden="true"></span>&nbsp;Exit</a>
    </nav>
</div>