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
    <c:if test="${not empty exceptions}">
       <c:forEach items="${exceptions}" var="exception">
         <br/><br/>
         <span style="color:red;display:inline-block;width:60%;">
         Field ${exception.fieldName} has invalid value ${exception.invalidValue}:  ${exception.validationMessage}</span>
       </c:forEach>
    </c:if>

   <c:if test="${not empty blankFieldValue}">
    <span style="color:red"> To make comparison, text must contain at least one letter.</span>
   </c:if>

    <c:if test="${empty exceptions && empty blankFieldValue}">
      <p>Result of comparison:
      <span style="font-style:italic">"${subject}"</span> and <span style="font-style:italic">"${anagram}"</span></p>

        <c:choose>
          <c:when test="${isValidAnagram}">
              <span style="color:green">A valid anagram!</span><br />
          </c:when>
        <c:otherwise>
              <span style="color:red">Not valid anagram!</span><br />
         </c:otherwise>
        </c:choose>
      </c:if>

  </c:if>
</body>
</html>