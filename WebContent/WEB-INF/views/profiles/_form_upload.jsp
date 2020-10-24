<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルアップロード</title>
</head>
<body>
    <form method="POST" enctype="multipart/form-data" action="../image/upload">
        <input type="file" name="file"/><br/><br/>
        <input type="submit" value="アップロード" /><br/><br/>
    </form>

</body>
