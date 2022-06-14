<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>GuestBook</title>
	</head>
	<body>
		<form action="/guestbook3/add" method="post">
			<table border="1" style="width:400px">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value=""></td>
					<td>비밀번호</td>
					<td><input type="password" name="password" value=""></td>
				</tr>
				<tr>
					<td colspan="4"><textarea name="content" style="width:400px"></textarea></td>
				</tr>
				<tr>
					<td colspan="4"><button type="submit">확인</button></td>
				</tr>
			</table>
		</form>
		<br>
		
		<c:forEach items="${gList }" var="g" varStatus="status">
			<table border="1" style="width:400px">
				<tr>
					<td style="width:30px">${g.no }</td>
					<td style="width:100px">${g.name }</td>
					<td style="width:220px">${g.regDate }</td>
					<td style="width:50px"><a href="/guestbook3/deleteForm/${g.no }">[삭제]</a></td>
				</tr>
				<tr>
					<td colspan="4">${g.content }</td>
				</tr>
			</table>
			<br>
		</c:forEach>
	</body>
</html> 