<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0">
        <title>プロスピA｜ファンサイト</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">

                <div id="header_menu">
                    <h1><a href="<c:url value='/' />">プロスピA｜ファンサイト</a></h1>&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value='/users/index' />">ユーザー一覧</a>&nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.login_user != null}">
                        <%-- 管理者権限付与時に表示される
                        <c:if test="${sessionScope.login_user.admin_flag == 1}">
                        <a href="<c:url value='/users/index' />">マイページ</a>&nbsp;
                        </c:if>
                        <a href="<c:url value='/reports/index' />">日報管理</a>&nbsp;
                         --%>
                    </c:if>
                </div>

                <c:if test="${sessionScope.login_user == null}">
                    <div id="user_name">
                        <a href="<c:url value='/users/new' />">新規登録(無料)</a>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/login' />">ログイン</a>
                    </div>
                </c:if>

                <c:if test="${sessionScope.login_user != null}">
                    <%--
                    <<div id="user_name">
                        <a href="<c:url value='/users/edit?id=${sessionScope.login_user.id}' />"><c:out value="${sessionScope.login_user.user_name}" /></a>さん&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                     --%>
                    <div id="user_name">
                        <a href="<c:url value='/mypage?id=${sessionScope.login_user.id}' />">マイページ</a>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>

            </div>
            <div id="content">
                ${param.content}
            </div>
<%--
            <h3 class="heading">↓↓以下はガチ勢向けアイテムです</h3>
            <table class="sampleTable">
            <tr>
            <td align="center" width="200"><div style="border:1px solid #95a5a6;border-radius:.75rem;background-color:#FFFFFF;width:138px;margin:0px;padding:5px 0;text-align:center;overflow:hidden;"><a href="https://hb.afl.rakuten.co.jp/ichiba/1cb47f75.c6cada54.1cb47f76.a9236cda/?pc=https%3A%2F%2Fitem.rakuten.co.jp%2Fapnshop%2Ffinger-stall%2F&link_type=picttext&ut=eyJwYWdlIjoiaXRlbSIsInR5cGUiOiJwaWN0dGV4dCIsInNpemUiOiIxMjh4MTI4IiwibmFtIjoxLCJuYW1wIjoiZG93biIsImNvbSI6MSwiY29tcCI6ImRvd24iLCJwcmljZSI6MSwiYm9yIjoxLCJjb2wiOjEsImJidG4iOjAsInByb2QiOjB9" target="_blank" rel="nofollow sponsored noopener" style="word-wrap:break-word;"  ><img src="https://hbb.afl.rakuten.co.jp/hgb/1cb47f75.c6cada54.1cb47f76.a9236cda/?me_id=1304818&item_id=10000130&pc=https%3A%2F%2Fthumbnail.image.rakuten.co.jp%2F%400_mall%2Fapnshop%2Fcabinet%2F05941186%2Fimgrc0142066814.jpg%3F_ex%3D128x128&s=128x128&t=picttext" border="0" style="margin:2px" alt="[商品価格に関しましては、リンクが作成された時点と現時点で情報が変更されている場合がございます。]" title="[商品価格に関しましては、リンクが作成された時点と現時点で情報が変更されている場合がございます。]"></a><p style="font-size:12px;line-height:1.4em;text-align:left;margin:0px;padding:2px 6px;word-wrap:break-word"><a href="https://hb.afl.rakuten.co.jp/ichiba/1cb47f75.c6cada54.1cb47f76.a9236cda/?pc=https%3A%2F%2Fitem.rakuten.co.jp%2Fapnshop%2Ffinger-stall%2F&link_type=picttext&ut=eyJwYWdlIjoiaXRlbSIsInR5cGUiOiJwaWN0dGV4dCIsInNpemUiOiIxMjh4MTI4IiwibmFtIjoxLCJuYW1wIjoiZG93biIsImNvbSI6MSwiY29tcCI6ImRvd24iLCJwcmljZSI6MSwiYm9yIjoxLCJjb2wiOjEsImJidG4iOjAsInByb2QiOjB9" target="_blank" rel="nofollow sponsored noopener" style="word-wrap:break-word;"  >CoDモバイル、荒野行動 PUBG Mobile対応 8個セット（8枚入） スマ...</a><br><span >価格：1090円（税込、送料無料)</span> <span style="color:#BBB">(2020/8/18時点)</span></p></div><br><p style="color:#000000;font-size:12px;line-height:1.4em;margin:5px;word-wrap:break-word">これで打率アップ!!<br>タブレット勢を打ち負かせ!<br></p></td>
            <td align="center"><div style="border:1px solid #95a5a6;border-radius:.75rem;background-color:#FFFFFF;width:138px;margin:0px;padding:5px 0;text-align:center;overflow:hidden;"><a href="https://hb.afl.rakuten.co.jp/ichiba/1cb485b0.da676b7a.1cb485b1.f85fb58f/_RTLink9622?pc=https%3A%2F%2Fitem.rakuten.co.jp%2Frakuten24%2F4953103346222%2F&link_type=picttext&ut=eyJwYWdlIjoiaXRlbSIsInR5cGUiOiJwaWN0dGV4dCIsInNpemUiOiIxMjh4MTI4IiwibmFtIjoxLCJuYW1wIjoiZG93biIsImNvbSI6MSwiY29tcCI6ImRvd24iLCJwcmljZSI6MSwiYm9yIjoxLCJjb2wiOjEsImJidG4iOjAsInByb2QiOjB9" target="_blank" rel="nofollow sponsored noopener" style="word-wrap:break-word;"  ><img src="https://hbb.afl.rakuten.co.jp/hgb/1cb485b0.da676b7a.1cb485b1.f85fb58f/?me_id=1261122&item_id=10856466&pc=https%3A%2F%2Fthumbnail.image.rakuten.co.jp%2F%400_mall%2Frakuten24%2Fcabinet%2F222%2F4953103346222.jpg%3F_ex%3D128x128&s=128x128&t=picttext" border="0" style="margin:2px" alt="[商品価格に関しましては、リンクが作成された時点と現時点で情報が変更されている場合がございます。]" title="[商品価格に関しましては、リンクが作成された時点と現時点で情報が変更されている場合がございます。]"></a><p style="font-size:12px;line-height:1.4em;text-align:left;margin:0px;padding:2px 6px;word-wrap:break-word"><a href="https://hb.afl.rakuten.co.jp/ichiba/1cb485b0.da676b7a.1cb485b1.f85fb58f/_RTLink9622?pc=https%3A%2F%2Fitem.rakuten.co.jp%2Frakuten24%2F4953103346222%2F&link_type=picttext&ut=eyJwYWdlIjoiaXRlbSIsInR5cGUiOiJwaWN0dGV4dCIsInNpemUiOiIxMjh4MTI4IiwibmFtIjoxLCJuYW1wIjoiZG93biIsImNvbSI6MSwiY29tcCI6ImRvd24iLCJwcmljZSI6MSwiYm9yIjoxLCJjb2wiOjEsImJidG4iOjAsInByb2QiOjB9" target="_blank" rel="nofollow sponsored noopener" style="word-wrap:break-word;"  >スマホ・タブレット用タッチペン 超感度タイプ ゲーミングスタイラス 黒 P-TP...</a><br><span >価格：2178円（税込、送料別)</span> <span style="color:#BBB">(2020/8/18時点)</span></p></div><br><p style="color:#000000;font-size:12px;line-height:1.4em;margin:5px;word-wrap:break-word">タッチペン派の方には<br>こちらがおススメ<br></p></td>
            <td align="center"><div style="border:1px solid #95a5a6;border-radius:.75rem;background-color:#FFFFFF;width:138px;margin:0px;padding:5px 0;text-align:center;overflow:hidden;"><a href="https://hb.afl.rakuten.co.jp/ichiba/1cb49707.7d3b1f2e.1cb49708.6c1706a5/?pc=https%3A%2F%2Fitem.rakuten.co.jp%2Fauc-worldichi%2F10003788%2F&link_type=picttext&ut=eyJwYWdlIjoiaXRlbSIsInR5cGUiOiJwaWN0dGV4dCIsInNpemUiOiIxMjh4MTI4IiwibmFtIjoxLCJuYW1wIjoiZG93biIsImNvbSI6MSwiY29tcCI6ImRvd24iLCJwcmljZSI6MSwiYm9yIjoxLCJjb2wiOjEsImJidG4iOjAsInByb2QiOjB9" target="_blank" rel="nofollow sponsored noopener" style="word-wrap:break-word;"  ><img src="https://hbb.afl.rakuten.co.jp/hgb/1cb49707.7d3b1f2e.1cb49708.6c1706a5/?me_id=1263126&item_id=10003788&pc=https%3A%2F%2Fthumbnail.image.rakuten.co.jp%2F%400_mall%2Fauc-worldichi%2Fcabinet%2Fsyouhinkategori01%2Fpc01%2F03722742%2Fimgrc0074165860.jpg%3F_ex%3D128x128&s=128x128&t=picttext" border="0" style="margin:2px" alt="[商品価格に関しましては、リンクが作成された時点と現時点で情報が変更されている場合がございます。]" title="[商品価格に関しましては、リンクが作成された時点と現時点で情報が変更されている場合がございます。]"></a><p style="font-size:12px;line-height:1.4em;text-align:left;margin:0px;padding:2px 6px;word-wrap:break-word"><a href="https://hb.afl.rakuten.co.jp/ichiba/1cb49707.7d3b1f2e.1cb49708.6c1706a5/?pc=https%3A%2F%2Fitem.rakuten.co.jp%2Fauc-worldichi%2F10003788%2F&link_type=picttext&ut=eyJwYWdlIjoiaXRlbSIsInR5cGUiOiJwaWN0dGV4dCIsInNpemUiOiIxMjh4MTI4IiwibmFtIjoxLCJuYW1wIjoiZG93biIsImNvbSI6MSwiY29tcCI6ImRvd24iLCJwcmljZSI6MSwiYm9yIjoxLCJjb2wiOjEsImJidG4iOjAsInByb2QiOjB9" target="_blank" rel="nofollow sponsored noopener" style="word-wrap:break-word;"  >新品 Apple/アップル iPad 10.2インチ 第7世代 Wi-Fi 32...</a><br><span >価格：40000円（税込、送料無料)</span> <span style="color:#BBB">(2020/8/18時点)</span></p></div><br><p style="color:#000000;font-size:12px;line-height:1.4em;margin:5px;word-wrap:break-word">もうこれを機会に<br>買ってしまうか!!<br></p></td>
            </tr>
            </TABLE>

--%>
            <div id="footer">
                Produced by とも坊
            </div>
        </div>
    </body>
</html>
