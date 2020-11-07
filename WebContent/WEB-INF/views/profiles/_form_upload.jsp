<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- errorsがある場合は表示する --%>
<c:if test="${sessionScope.errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${sessionScope.errors}">
            <c:out value="${sessionScope.errors}" /><br />
        </c:forEach>
    </div>
</c:if>

<input type="file" name="file"/><br/><br/>
<input type="submit" value="アップロード" /><br/><br/>
