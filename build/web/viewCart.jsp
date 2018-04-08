<%-- 
    Document   : viewCart
    Created on : Oct 21, 2017, 11:02:05 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script> 
        <script type="text/javascript" src="https://cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
        <script>
          webshims.setOptions('forms-ext', {types: 'date'});
          webshims.polyfill('forms forms-ext');
          $.webshims.formcfg = {
            en: {
                dFormat: '-',
                dateSigns: '-',
                patterns: {
                    d: "mm-dd-yy"
                }
            }
          };
        </script>
    </head>
    <body>
        <h1>Booking Details</h1>
        <c:set var="result" value="${sessionScope.CART}"/>
        <c:if test="${not empty result}">
            <c:set var="items" value="${result.items}"/>
            <c:if test="${not empty items}">
            <h2>Your order information includes</h2>
        <form action="Cart">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Room</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${items}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${item.key}</td>
                            <td>
                                <c:if test="${item.value.isHourPrice}">
                                    ${item.value.hourPrice}
                                </c:if>
                                <c:if test="${!item.value.isHourPrice}">
                                    ${item.value.dayPrice}
                                </c:if>
                            </td>
                            <td>
                                ${item.value.quantiy}
                            </td>
                            <td>
                                <input type="checkbox" name="chkAction" value="${item.key}"/>
                            </td>
                        </tr>
                    </c:forEach>                
                </tbody>
                
            </table>
                <h4>Total:${result.totalPrice}</h4>
                <br>
                <c:set var="error" value="${requestScope.ERRORSETDATE}"/>
                <c:if test="${not empty error}">
                    <c:if test="${not empty error.errSetDateduplicate}">
                        <font color="red">${error.errSetDateduplicate}</font><br>
                    </c:if>
                    <c:if test="${not empty error.errDateFormFormat}">
                        <font color="red">${error.errDateFormFormat}</font><br>
                    </c:if>
                    <c:if test="${not empty error.errSetDateInPast}">
                        <font color="red">${error.errSetDateInPast}</font><br>
                    </c:if>
                </c:if>
                Set date:<select name="hourFrom">
                            <option>07</option>
                            <option>08</option>
                            <option>09</option>
                            <option>10</option>
                            <option>11</option>
                            <option>12</option>
                            <option>13</option>
                            <option>14</option>
                            <option>15</option>
                            <option>16</option>
                            <option>17</option>
                            <option>18</option>
                            <option>19</option>
                            <option>20</option>
                            <option>21</option>
                            <option>22</option>
                            <option>23</option>
                            <option>24</option>
                        </select>:00 Hour
                <input type="date" name="dateFrom" /><br> <br>
                <input type="submit" value="Book More" name="btnAction"/>
                <input type="submit" value="Remove Room" name="btnAction"/>
                <input type="submit" value="Rent" name="btnAction"/>
        </form>
            </c:if>
            <c:if test="${empty items}">
                <h2>No item in Cart</h2>
                <a href="search.jsp">Click here to book room</a>
            </c:if>
        </c:if>
        <c:if test="${empty result}">
            <h2>No item in Cart</h2>
            <a href="search.jsp">Click here to book room</a>
        </c:if>
    </body>
</html>
