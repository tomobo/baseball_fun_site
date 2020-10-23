<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <%--
            <div id="flush_success">
                <c:out value="${sessionScope.id}"></c:out>
            </div>
         --%>

        <h2>パスワードリセット</h2>
        <a>新しいパスワードを入力してください。</a>
        <%-- POST形式でServletを呼び出し --%>
        <form method="POST" action="<c:url value='/passwordupdate' />">
           <c:import url="_form_password.jsp" />
        </form>

        <p><a href="<c:url value='/' />">Topへ戻る</a></p>
    </c:param>
</c:import>