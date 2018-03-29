<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/>
    <script src='<c:url value="/web_resources/js/custom/findFriend.js"/>'></script>
</head>
<body>
<div class="container">
    <div>
        <c:import url="fragments/bodyHeader.jsp"/>
    </div>
    <div>
        <form id="findFriendForm" method="post"
              action="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}">
            <div class="row">
                <div class="col-4 offset-4">
                    <h2>Search your friends</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-4">
                    <label>Name
                        <input type="text" name="name" placeholder="Name"></label>
                </div>
                <div class="col-4">
                    <label>Surname
                        <input type="text" name="surname" placeholder="Surname"></label>
                </div>
                <div class="col-4">
                    <label>Patronymic
                        <input type="text" name="patronymic" placeholder="Patronymic"></label>
                </div>
            </div>
            <div class="row">
                <div class="col-2 offset-9">
                    <input class="btn btn-lg btn-primary btn-block" type="submit" value="find">
                </div>
            </div>
        </form>
    </div>
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
                <tbody data-path-for-essence-friend="${pageContext.request.contextPath}${homepage}/${role}/friend_request">
                <c:forEach items="${friends}" var="userFriend" varStatus="status">
                    <tr data-essence-id="${userFriend.userEssenceId}">
                        <td>
                            <a href="${pageContext.request.contextPath}/profile/<c:out value="${userFriend.nickname}"/>">${userFriend.nickname}</a>
                        </td>
                        <td><input class="essence-friend-checkbox" type="checkbox"
                            <%--<ctg:map_contains essenceMap="${user.requestedFriendsFrom}" userEssence="${friend.userEssenceId}" text="checked=\"true\""/>--%>
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
</body>
</html>
