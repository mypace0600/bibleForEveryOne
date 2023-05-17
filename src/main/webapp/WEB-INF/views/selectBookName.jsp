<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>
<c:forEach items="${bookNameList}" var="bookData">
    <div class="bookName" id="${bookData.bookNum}">
        <span>${bookData.bookName}</span>
        <c:forEach items="${bookData.chapterNumberList}" var="chapterNum">
            <span>${chapterNum}</span>
        </c:forEach>

    </div>
</c:forEach>
<script src="/js/search.js"></script>
<%@include file="layout/footer.jsp"%>
