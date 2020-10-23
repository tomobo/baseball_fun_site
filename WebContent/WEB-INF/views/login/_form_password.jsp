<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<%--
<label for="id">id</label><br />
<input type="text" name="id" value="${sessionScope.id}" />
<br /><br />
 --%>
<label for="password">パスワード</label><br />
<input type="password" name="password" value="" />
<br /><br />

<%--SubmitボタンでServletを呼び出し --%>
<button type="submit">パスワードを更新する</button>