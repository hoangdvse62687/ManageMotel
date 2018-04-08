<%-- 
    Document   : viewOrder
    Created on : Oct 23, 2017, 8:58:41 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail Page</title>
    </head>
    <body>
        <h1>Order Page</h1>
        <c:set var="detailOrder" value="${requestScope.ORDERDETAILCART}"/>
        <c:if test="${not empty detailOrder}">
            Customer Name:${detailOrder.customerName}<br><br>
            Order Date:${detailOrder.orderDate}<br><br>
            From Date:${detailOrder.fromDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            To Date:${detailOrder.toDate}<br><br>
            <c:set var="result" value="${requestScope.ORDERCART}"/>
            <c:if test="${not empty result}">
                <h3>Details</h3>
                <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Room</th>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                <c:forEach var="item" items="${result.items}" varStatus="counter">                        
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.key}</td>
                                <td>${item.value.quantiy}</td>
                                <td>
                                    <c:if test="${item.value.isHourPrice}">
                                        ${item.value.hourPrice}
                                    </c:if>
                                    <c:if test="${!item.value.isHourPrice}">
                                        ${item.value.dayPrice}
                                    </c:if>
                                </td>
                            </tr>               
                </c:forEach>
                        </tbody>
                </table>
                <h3>Total Price:${result.totalPrice}</h3>
                <font color="red">
                    Thank you we hope to see you soon
                    </font><br>
                <a href="search.jsp">Click Here to back Home</a>
            </c:if>
        </c:if>
    </body>
</html>


