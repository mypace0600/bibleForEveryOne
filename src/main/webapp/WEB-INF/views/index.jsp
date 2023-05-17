<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<body>
<h1>${bookName} ${chapterNum}장</h1>
<c:forEach items="${bibleContentDataList}" var="bibleContent">
    <div>
    <span>${bibleContent.verse}</span>
    <span>${bibleContent.text}</span>
    </div>
</c:forEach>
</body>
</html>
