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

        <h2>マイページ</h2>

        <c:choose>
            <c:when test="${sessionScope.image != null}">
                <p>プロフィール画像</p>
                   <%-- DBにプロフィール画像のファイルパスがある場合は画像を表示 --%>
                   <div><img src="<c:url value='${sessionScope.image}' />" width="100" height="90" alt=プロフィール画像 />
                   </div>
        <%--
        <img src="<c:url value='${profile_image_name}'/>" width="800" height="350" />
        <img src="<c:url value='/image/uploaded/さかたともや.png'/>" width="800" height="350" />
        <img src="<c:url value='C:/pleiades/workspace/baseball_fun_site/WebContent/image/uploaded/さかたともや.png'/>" width="800" height="350" />
        <img src="C:\pleiades\workspace\baseball_fun_site\WebContent\image\banners\topics_img_top.jpg" width="800" height="350" />
        <img src="<c:url value='/image/banners/topics_img_top.jpg'/>" width="800" height="350" alt=トップページバナー画像/>
        --%>

                <p>基本情報</p>
                   <div>ベースボールID | ${user.bbid}<br>
                        ユーザーネーム | ${user.user_name}<br>
                        メールアドレス | ${user.mail_address}<br>
                   </div>
            </c:when>

            <c:when test="${user != null}">
                <p>基本情報</p>
                   <div>ベースボールID | ${user.bbid}<br>
                        ユーザーネーム | ${user.user_name}<br>
                        メールアドレス | ${user.mail_address}<br>
                   </div>
            </c:when>

            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>


        <c:if test="${sessionScope.login_user != null}">
            <div id="upload_profile">
                <p><a href="<c:url value='/profile/create' />">プロフィール画像の設定はコチラ</a></p>
            </div>
            <div id="edit_user">
                <p><a href="<c:url value='/users/edit?id=${sessionScope.login_user.id}' />">基本情報の変更はコチラ</a></p>
            </div>
        </c:if>


        <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>