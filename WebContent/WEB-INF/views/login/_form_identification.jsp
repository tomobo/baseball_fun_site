<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <c:if test="${errors != null}">
        <div id="flush_error">
        入力内容にエラーがあります。<br />
            <c:forEach var="error" items="${errors}">
                <c:out value="${error}" /><br />
            </c:forEach>
        </div>
    </c:if>

    <%--本人確認のため、ユーザーIDとメールアドレスを入力 --%>
    <%--ログインしていないので、valueは空のままでOK --%>
    <label for="bbid">ベースボールID</label><br />
    <input type="text" name="bbid" value="" /><br /><br />

    <label for="mail_address">メールアドレス</label><br />
    <input type="text" name="mail_address" value="" /><br /><br />

    <%--SubmitボタンでServletを呼び出し --%>
    <button type="submit">本人確認をする</button>
