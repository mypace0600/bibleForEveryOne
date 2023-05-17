<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>
    <a href="/select/book"><h1>${bookName} ${chapterNum}장</h1></a>
    <c:forEach items="${bibleContentDataList}" var="bibleContent">
    <div>
    <span>${bibleContent.verse}. </span>
    <span>${bibleContent.text}</span>
    </div>
</c:forEach>
<%@include file="layout/footer.jsp"%>
