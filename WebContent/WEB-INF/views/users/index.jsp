<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>ユーザー　一覧</h2>
        <table id="user_list">
            <tbody>
                <tr>
                    <th>ユーザー名</th>
                    <th>プロフィール</th>
                </tr>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <c:choose>
                        <c:when test="${user.delete_flag == 0}">
                            <tr class="row${status.count % 2}">
                            <td><a href="<c:url value='/users/show?id=${user.id}' />"><c:out value="${user.user_name}" /></a></td>
                            <td><a href="<c:url value='/users/show?id=${user.id}' />">詳細を表示</a></td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${users_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((users_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/users/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <c:if test="${sessionScope.login_user == null}">
            <p><a href="<c:url value='/users/new' />">新規ユーザー登録</a></p>
            <p><a href="<c:url value='/' />">Topページへ戻る</a></p>
        </c:if>

        <c:if test="${sessionScope.login_user != null}">
            <p><a href="<c:url value='/' />">Topページへ戻る</a></p>
        </c:if>

    </c:param>
</c:import>