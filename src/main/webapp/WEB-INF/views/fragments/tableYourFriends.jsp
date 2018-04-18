<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<table class="table table-hover">
    <caption>Your friends</caption>
    <thead>
    <tr>
        <th>Nickname</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Role</th>
        <th>Friend State</th>
        <th>Delete Request</th>
    </tr>
    </thead>
    <c:set var="user" value="${requestScope.user}"/>
    <tbody>
    <c:forEach items="${user != null ? ctg:getFriends(user) : null}" var="userFriend">
        <c:set var="user" value="${requestScope.user.requestedFriendsTo.contains(userFriend) ? userFriend.userEssence : userFriend.friend}"/>
        <tr data-essence-id="${user.userEssenceId}">
            <td>
                <a href="${pageContext.request.contextPath}/profile/<c:out value="${user.nickname}"/>">${user.nickname}</a>
            </td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td>
                <c:choose>
                    <c:when test="${requestScope.user.requestedFriendsTo.contains(userFriend)}">
                        <select class="action-friends custom-select" name="action-friends" required>
                            <c:set var="stateFriendSet" value="<%=com.pet_space.models.essences.StateFriend.StateFriendEnum.values()%>"/>
                            <c:forEach items="${stateFriendSet}" var="stateFriend" varStatus="status">
                                <option value="<c:out value="${stateFriend}"/>"
                                        <c:if test="${userFriend.state.stateFriendEnum.equals(stateFriend)}">selected</c:if>>
                                    <c:out value="${stateFriend}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <label><c:out value="${userFriend.state}"/></label>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="${not requestScope.user.requestedFriendsTo.contains(userFriend)}">
                    <button class="delete-request-friend-of-button">
                        <img src="<c:url value="/web_resources/images/delete.ico"/>" alt="delete request friend" width="8">
                    </button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>