<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルアップロード</title>
</head>

<body>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <%-- flushがある場合は表示する --%>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <form method="POST" enctype="multipart/form-data" action="<c:url value='/image/upload' />">
            <c:import url="_form_upload.jsp" />
        </form>

    </c:param>
</c:import>
</body>