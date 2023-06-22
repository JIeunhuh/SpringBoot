<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글 등록</title>
</head>
<body>
	<h3>새 글 등록하기</h3>
	<hr>
	<form action="insertBoard" method="post">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="lightblue" width="70">제목</td>
				<td align="left"><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td bgcolor="lightblue">작성자</td>
				<td align="left"><input type="text" name="writer" /></td>
			</tr>
			<tr>
				<td bgcolor="pink">내용</td>
				<td align="left"><textarea name="content" cols="40" rows="10" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="새 글 등록"/>
				</td>
			</tr>

		</table>
		</form>
</body>
</html>