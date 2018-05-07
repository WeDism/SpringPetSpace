<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<html>
<head>
    <c:import url="fragments/htmlHeadTags.jsp"/>
    <script src='<c:url value="/web_resources/js/custom/message.js"/>'></script>
</head>
<body>
<c:set var="currentUserRole" value="${fn:toLowerCase(requestScope.user.role)}"/>
<div class="container" data-context-path="${pageContext.request.contextPath}/${currentUserRole}">
    <c:import url="fragments/bodyHeader.jsp"/>
    <div class="row c-row">
        <div class="col">
            <div class="card-deck col-12">
                <div class="col-3">
                    <div class="card">
                        <div class="card-header">Friends contacts</div>
                        <div class="card-body">
                            <ul class="list-unstyled">
                                <c:forEach items="${friends}" var="userFriend">
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="custom-control custom-checkbox">
                                                <input class="custom-control-input" type="checkbox" id="${userFriend.nickname}">
                                                <label class="custom-control-label" for="${userFriend.nickname}">${userFriend.surname}&nbsp;${userFriend.name}&nbsp;
                                                    <small>(${userFriend.role})</small>
                                                </label>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-9">
                    <div class="card" data-path-for-message="${pageContext.request.contextPath}/${currentUserRole}/messages"
                         data-author-nickname="<c:url value="${user.nickname}"/>"
                         data-path-to-profile="${pageContext.request.contextPath}/profile/<c:url value="${user.nickname}"/>">
                        <div class="card-header">Messages</div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col">
                                        <textarea id="textMessage" class="form-control" placeholder="Enter here for write new message" name="text"
                                                  rows="3"></textarea>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col">
                                    <button id="sendMessage" type="button" class="btn float-right">
                                        <span class="oi oi-envelope-open"></span>&nbsp;Send message
                                    </button>
                                </div>
                            </div>
                            <br/>
                            <ul class="list-unstyled">
                                <c:forEach var="message" items="${messages}">
                                <div class="row">
                                        <c:set var="messageFromCurrentUser" value="${false}"/>
                                    <c:choose>
                                    <c:when test="${message.author.equals(user)}">
                                    <div class="col-6 offset-6">
                                        <c:set var="messageFromCurrentUser" value="${true}"/>
                                        </c:when>
                                        <c:otherwise>
                                        <div class="col-6">
                                            </c:otherwise>
                                            </c:choose>
                                            <li class="media">
                                                <div class="media-body">
                                                    <div class="text-muted float-right">
                                                        <small>
                                                            <fmt:parseDate value="${message.date}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime"/>
                                                            <fmt:formatDate pattern="HH:mm:ss" var="formatTime" value="${parsedDateTime}"/>
                                                            <div class="float-right" title="${formatTime}">&nbsp;${fn:substring(message.date,0,10)}</div>
                                                            <a class="float-right" href="<c:url value="/profile/${message.author.nickname}/"/>">
                                                                author <c:out value="${message.author.nickname}"/>
                                                            </a>
                                                        </small>
                                                    </div>
                                                    <c:choose>
                                                        <c:when test="${messageFromCurrentUser}">
                                                            <strong class="text-success">to ${ctg:ownersToString(message, user, messageFromCurrentUser)}</strong>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <strong class="text-info">to me ${ctg:ownersToString(message, user, messageFromCurrentUser)}</strong>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <p><c:out value="${message.text}"/></p>
                                                </div>
                                            </li>
                                        </div>
                                    </div>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
