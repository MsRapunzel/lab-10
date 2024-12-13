<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Send JMS Message</title>
    </head>
    <body>
        <a href="index.jsp" >GO home</a>
        <h1>Send JMS Message</h1>
        <form action="send-message" method="post">
            <label for="messageText">Message Text:</label>
            <input type="text" id="messageText" name="messageText" required><br>

            <label for="messageSelector">Message Selector (optional):</label>
            <select id="messageSelector" name="messageSelector">
                <option value="">No Selector</option>
                <option value="Priority High">High Priority</option>
                <option value="Priority Low">Low Priority</option>
                <option value="MessageType = 'urgent'">Urgent Messages</option>
                <option value="MessageType = 'normal'">Normal Messages</option>
            </select><br>

            <input type="submit" value="Send Message">
        </form>

        <h2>Last Sent Message</h2>
        <jsp:useBean id="messageSenderBean" class="com.msrapunzel.MessageSenderBean" scope="application"/>
        <c:if test="${not empty messageSenderBean.lastMessageText}">
            <p>Text: ${messageSenderBean.lastMessageText}</p>
            <p>Selector: ${messageSenderBean.lastMessageSelector}</p>
        </c:if>
    </body>
</html>
