<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table class="table table-hover">
    <caption>Your friends</caption>
    <thead>
    <tr>
        <th>Nickname</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Role</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody data-path-for-state-friend="${pageContext.request.contextPath}${homepage}/friend_controller">
    <c:set var="user" value="${sessionScope.user}"/>
    <c:forEach items="${user.getRequestedFriendsFrom()}" var="friend">
        <tr data-essence-id="${friend.userEssenceId}">
            <td>
                <a href="<c:url value="${homepage}"/>/essence?nickname=<c:out value="${friend.nickname}"/>">${friend.nickname}</a>
            </td>
            <td><c:out value="${friend.name}"/></td>
            <td><c:out value="${friend.surname}"/></td>
            <td><c:out value="${friend.role}"/></td>
            <td>
                <c:choose>
                    <c:when test="${user.getRequestedFriendsTo.containsKey(friend.userEssenceId)}">
                        <select class="action-friends" name="action-friends" required>
                            <c:set var="stateFriendSet"
                                   value="<%=com.pet_space.models.essences.StateFriend.StateFriendEnum.values()%>"/>
                            <c:forEach items="${stateFriendSet}" var="stateFriend" varStatus="status">
                                <option value="<c:out value="${stateFriend}"/>"
                                        <c:if test="${user.getRequestedFriendsTo.get(friend.userEssenceId).equals(stateFriend)}">selected</c:if>>
                                    <c:out value="${stateFriend}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <label><c:out value="${sessionScope.user.getRequestedFriendsFrom.get(friend.userEssenceId)}"/></label>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>