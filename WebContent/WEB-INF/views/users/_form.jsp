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
<label for="bbid">ベースボールID</label><br />
<input type="text" name="bbid" value="${user.bbid}" />
<br /><br />

<label for="user_name">ユーザー名</label><br />
<input type="text" name="user_name" value="${user.user_name}" />
<br /><br />

<label for="mail_address">メールアドレス</label><br />
<input type="text" name="mail_address" value="${user.mail_address}" />
<br /><br />

<label for="password">パスワード</label><br />
<input type="password" name="password" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>