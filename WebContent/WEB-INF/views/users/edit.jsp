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


        <c:choose>
            <c:when test="${user != null}">
                <h2>${user.user_name}さん | 基本情報</h2>
                <p>※基本情報は公開されません。
                <br>※パスワードは変更する場合のみ入力してください。</p>
                <form method="POST" action="<c:url value='/users/update' />">
                    <c:import url="_form.jsp" />
                </form>
                <%--
                <c:if test="${sessionScope.login_user != null}">
                    <div id="profile_new">
                         <p><a href="<c:url value='/profile/new' />">→プロフィール編集へ</a></p>&nbsp;
                    </div>
                </c:if>

                <form method="POST" action="<c:url value='/users/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                 --%>

                <p><a href="#" onclick="confirmDestroy();">このユーザー情報を削除する</a></p>
                <form method="POST" action="<c:url value='/users/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script>
                    function confirmDestroy() {
                        if(confirm("本当に削除してよろしいですか？")) {
                            document.forms[1].submit();
                        }
                    }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>