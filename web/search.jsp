<%-- 
    Document   : search
    Created on : Oct 9, 2017, 9:17:04 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <form action="LogOut">
            <h1>Welcome to ABC Hotels</h1><br>
            <font color="red">Welcome,${sessionScope.USERNAME}</font>
            <input type="submit" value="Log Out" />
        </form>
        <br>
        <c:set var="error" value="${requestScope.ERRORSEARCH}"/>
        <form action="Search">
            Floor <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Để trống - search tất cả <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;
            <c:if test="${not empty error}">
                <font color="red">
                    ${error.errNumberFloor}
                    </font><br>
            </c:if>
            <input type="submit" value="Search" name="btnAction" />
        </form>
            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:if test="${empty error}">
                <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
                <c:set var="errorTypePrice" value="${requestScope.ERRORTYPEPRICE}"/>
                <c:if test="${not empty result}">
                    <c:if test="${not empty errorTypePrice}">
                        <font color="red">
                            ${errorTypePrice.notSelectTypePrice}
                            </font><br>
                    </c:if>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Room</th>
                                <th>Description</th>
                                <th>Hour Price</th>
                                <th>Day Price</th>
                                <th>Floor</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="items" items="${result}" varStatus="counter">
                            <form action="Book">
                                <tr>
                                <td>${counter.count}</td>
                                <td>${items.roomID}
                                    <input type="hidden" name="roomID" value="${items.roomID}" />
                                </td>
                                <td>${items.description}</td>
                                <td>${items.hourPrice}
                                    <input type="hidden" name="hourPrice" value="${items.hourPrice}" />
                                    <select name="cboHourPrice">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                    </select>
                                    <input type="radio" name="chkTypePrice" value="hourChosen">
                                </td>
                                <td>${items.dayPrice}
                                    <input type="hidden" name="dayPrice" value="${items.dayPrice}" />
                                    <select name="cboDayPrice">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                    </select>
                                    <input type="radio" name="chkTypePrice" value="dayChosen">
                                </td>
                                <td>${items.floor}</td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    <c:if test="${!items.isBook}">
                                        <input type="submit" value="Book" />
                                    </c:if>
                                    <c:if test="${items.isBook}">
                                        <font color="red">Booked</font>
                                    </c:if>
                                </td>
                                </tr>
                            </form>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${not empty searchValue}">
                    <c:if test="${empty result}">
                    <h1>No room matching</h1>
                    </c:if>
                </c:if>
            </c:if>
            <a href="viewCart.jsp">View Booking Details</a>
    </body>
</html>
