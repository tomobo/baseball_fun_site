<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${user != null}">
                <h2> ${user.user_name} |プロフィール</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザー名</th>
                            <td><c:out value="${user.user_name}" /></td>
                        </tr>
                        <tr>
                            <th>プロフィール</th>
                            <td>調整中</td>
                                <%--権限カラム表示時に使用--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${employee.admin_flag == 1}">管理者</c:when>--%>
                                    <%--<c:otherwise>一般</c:otherwise>--%>
                                <%--</c:choose>--%>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${user.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${user.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <%-- ユーザー情報の変更はログイン後のマイページでのみ行えるようにする。
                <p><a href="<c:url value='/users/edit?id=${user.id}' />">このユーザー情報を編集する</a></p>--%>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>