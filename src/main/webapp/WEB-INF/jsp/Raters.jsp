<%-- 
    Document   : Raters
    Created on : Mar 29, 2016, 11:47:13 AM
    Author     : D062572
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="debate.rating.Rating" %>
<!DOCTYPE html>
<div>
    <c:if test="${requestScope.raters.size() == 0}">
        No raters</h5>
    </c:if>
    <c:if test="${requestScope.raters.size() > 0}">
        <c:forEach items="${requestScope.raters}" var="rating">
            <div>
                <c:if test="${rating.ratingValue == 'POSITIVE'}">
                    <i class="glyphicon glyphicon-thumbs-up" style="color: green;"></i>
                </c:if>
                <c:if test="${rating.ratingValue == 'NEGATIVE'}">
                    <i class="glyphicon glyphicon-thumbs-down" style="color: darkred;"></i>
                </c:if>
                ${rating.getUser().getUsername()}
            </div>     
        </c:forEach>
    </c:if>  
</div>
