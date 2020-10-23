<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <%--フラッシュメッセージがセットされていたら、そのメッセージをビューで表示する--%>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <%--デバック用--%>
        <%--
        <c:if test="${user_id != null and mail_address != null}">
            <div id="flush_success">
                <c:out value="${user_id}"></c:out>
                <c:out value="${mail_address}"></c:out>
                <c:out value="${id}"></c:out>
            </div>
        </c:if>
        --%>

        <div id="banner">
            <img src="<c:url value='/image/banners/topics_img_top.jpg'/>" width="800" height="350" alt=トップページバナー画像/>
        </div>

    </c:param>
</c:import>