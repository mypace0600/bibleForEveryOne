<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>
<c:forEach items="${chapterNumberList}" var="chapterData">
    <div class="chapterBox" id="${chapterData}">${chapterData}</div>
</c:forEach>
<script src="/js/search.js"></script>
<%@include file="layout/footer.jsp"%>
