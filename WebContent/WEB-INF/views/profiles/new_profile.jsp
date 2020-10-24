<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>プロフィール | 新規登録ページ</h2>

        <div>プロフィール画像を設定する場合は画像をファイルを選択し、画像をアップロードしてください。</div><br/>
        <c:import url="_form_upload.jsp" />

        <form method="POST" action="<c:url value='/profile/create' />">
            <c:import url="_form_profile.jsp" />
        </form>

        <p><a href="<c:url value='/' />">一覧に戻る</a></p>
    </c:param>
</c:import>