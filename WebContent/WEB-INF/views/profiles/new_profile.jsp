<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>プロフィール | 新規登録ページ</h2>

        <c:if test="${sessionScope.profile_image_name != null}">
            <img src="<c:url value='${sessionScope.profile_image_name}'/>" width="200" height="200" />
        </c:if>

        <div>プロフィール画像を設定する場合は画像をファイルを選択し、画像をアップロードしてください。</div><br/>
        <c:import url="_form_upload.jsp" />

        <%--
        <img src="<c:url value='${profile_image_name}'/>" width="800" height="350" />
        <img src="<c:url value='/image/uploaded/さかたともや.png'/>" width="800" height="350" />
        <img src="<c:url value='C:/pleiades/workspace/baseball_fun_site/WebContent/image/uploaded/さかたともや.png'/>" width="800" height="350" />
        <img src="C:\pleiades\workspace\baseball_fun_site\WebContent\image\banners\topics_img_top.jpg" width="800" height="350" />
        <img src="<c:url value='/image/banners/topics_img_top.jpg'/>" width="800" height="350" alt=トップページバナー画像/>
         --%>

        <form method="POST" action="<c:url value='/profile/create' />">
            <c:import url="_form_profile.jsp" />
        </form>

        <p><a href="<c:url value='/' />">一覧に戻る</a></p>
    </c:param>
</c:import>