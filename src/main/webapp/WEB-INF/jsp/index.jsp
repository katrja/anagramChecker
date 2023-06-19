<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
<title>AnagramChecker</title>
</head>
<body>
<h1>Check if two texts are anagram of each other:</h1>

    <form method="post" action="/anagram">
        Subject : <input type="text" name="subject" />
        Anagram : <input type="text" name="anagram" />
        <input type="submit" />
    </form>

  <c:if test="${pageContext.request.method=='POST'}">

    <p>Result of comparing
    <span style="font-style:italic">${subject}</span> and <span style="font-style:italic">${anagram}</span></p>

      <c:choose>
        <c:when test="${isValidAnagram}">
            <font color="green">A valid anagram!</font><br />
        </c:when>
      <c:otherwise>
            <font color="red">Not valid anagram!</font><br />
       </c:otherwise>
      </c:choose>
  </c:if>
</body>
</html>