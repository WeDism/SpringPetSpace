<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/>
    <script src='<c:url value="/web_resources/js/custom/findFriend.js"/>'></script>
</head>
<body>
<c:set var="currentUserRole" value="${fn:toLowerCase(requestScope.user.role)}"/>
<div class="container" data-context-path="${pageContext.request.contextPath}/${currentUserRole}">
    <c:import url="fragments/bodyHeader.jsp"/>
    <div class="row c-row">
        <div class="col">
            <form id="findFriendForm" method="post"
                  action="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}">
                <div class="row">
                    <div class="col-4 offset-4">
                        <h2>Search your friends</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-4">
                        <div class="form-group row">
                            <label class="col-4 col-form-label" for="name">Name</label>
                            <div class="col-8">
                                <input class="form-control" id="name" type="text" name="name" placeholder="Name">
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="form-group row">
                            <label class="col-4 col-form-label" for="surname">Surname</label>
                            <div class="col-8">
                                <input class="form-control" id="surname" type="text" name="surname" placeholder="Surname">
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="form-group row">
                            <label class="col-4 col-form-label" for="patronymic">Patronymic</label>
                            <div class="col-8">
                                <input class="form-control" id="patronymic" type="text" name="patronymic" placeholder="Patronymic">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-2 offset-9">
                        <input class="btn btn-lg btn-primary btn-block" type="submit" value="find">
                    </div>
                </div>
            </form>
            <c:set var="userFriends" value="${requestScope.userFriends}"/>
            <c:if test="${friends != null}">
                <div>
                    <table class="table table-hover">
                        <caption>Friends</caption>
                        <thead>
                        <tr>
                            <th>Nickname</th>
                            <th>Add friend request</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Patronymic</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Pets</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${friends}" var="userFriend" varStatus="status">
                            <tr data-essence-id="${userFriend.userEssenceId}">
                                <td>
                                    <a href="${pageContext.request.contextPath}/profile/<c:out value="${userFriend.nickname}"/>">${userFriend.nickname}</a>
                                </td>
                                <td><input class="essence-friend-checkbox" type="checkbox" <c:if test="${userFriends.contains(userFriend)}">checked="true"</c:if>/>
                                </td>
                                <td><c:out value="${userFriend.name}"/></td>
                                <td><c:out value="${userFriend.surname}"/></td>
                                <td><c:out value="${userFriend.patronymic}"/></td>
                                <td><c:out value="${userFriend.email}"/></td>
                                <td><c:out value="${userFriend.role}"/></td>
                                <td><c:out value="${userFriend.statusEssence}"/></td>
                                <td>
                                    <c:forEach items="${userFriend.pets}" var="pet" varStatus="status">
                                        <c:out value="${pet.name}"/>&nbsp;(<c:out value="${pet.genusPet.name}"/>)<br/>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
