<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>パスワードリセット</h2>
        <p>本人確認のため、ユーザーIDと登録時のメールアドレスを入力してください</p>
        <%-- POST形式でServletを呼び出し --%>
        <form method="POST" action="<c:url value='/indentification' />">
           <c:import url="_form_identification.jsp" />
        </form>

        <p><a href="<c:url value='/' />">Topへ戻る</a></p>
    </c:param>
</c:import>