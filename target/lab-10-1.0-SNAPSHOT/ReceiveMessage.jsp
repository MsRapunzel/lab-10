<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Receive JMS Message</title>
    </head>
    <body>
        <a href="index.jsp" >GO home</a>

        <h1>Receive JMS Message</h1>
        <form action="receive-message" method="post">
            <label for="messageSelector">Message Selector (optional):</label>
            <select id="messageSelector" name="messageSelector">
                <option value="">No Selector</option>
                <option value="Priority High">High Priority</option>
                <option value="Priority Low">Low Priority</option>
                <option value="MessageType = 'urgent'">Urgent Messages</option>
                <option value="MessageType = 'normal'">Normal Messages</option>
            </select><br>

            <input type="submit" value="Receive Message">
        </form>

        <h2>Last Received Message</h2>
        <jsp:useBean id="messageReceiverBean" class="com.msrapunzel.MessageReceiverBean" scope="application"/>
        <c:if test="${not empty messageReceiverBean.lastReceivedMessage}">
            <p>Text: ${messageReceiverBean.lastReceivedMessage}</p>
            <p>Used Selector: ${messageReceiverBean.lastUsedSelector}</p>
        </c:if>
    </body>
</html>