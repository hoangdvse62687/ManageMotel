<%-- 
    Document   : confirm
    Created on : Oct 23, 2017, 8:35:56 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Page</title>
    </head>
    <body>
        <h1>Confirm</h1>
        <c:set var="error" value="${requestScope.ERRORCONFIRM}"/>
        <form method="POST" action="Confirm">
            <c:if test="${not empty error.errCustomerNameLength}">
                <font color="red">${error.errCustomerNameLength}</font><br>
            </c:if>
            Customer Name:<input type="text" name="txtCustomerName" value="" /><br>
            <c:if test="${not empty error.errCarDIDLength}">
                <font color="red">${error.errCarDIDLength}</font><br>
            </c:if>
            <c:if test="${not empty error.errCardIDformat}">
                <font color="red">${error.errCardIDformat}</font><br>
            </c:if>
            Card ID: <input type="text" name="txtCardID" value="" /><br>
            <input type="submit" value="Confirm" name="btnAction"/>
            <input type="submit" value="Cancel" name="btnAction"/>
        </form>
    </body>
</html>
