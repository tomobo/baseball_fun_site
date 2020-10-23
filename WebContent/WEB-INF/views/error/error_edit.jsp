<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <%-- flushがある場合は表示する --%>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h3>エラーが発生しました。</h3>
        <h3>Topに戻り、再度実施してください。</h3>


        <p><a href="<c:url value='/' />">Topに戻る</a></p>
    </c:param>
</c:import>