package com.msrapunzel;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/send-message")
public class MessageServlet extends HttpServlet {

    @Inject
    private MessageSenderBean messageSenderBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String messageText = request.getParameter("messageText");
        String messageSelector = request.getParameter("messageSelector");

        messageSenderBean.sendMessage(messageText, messageSelector);

        response.sendRedirect("SendMessage.jsp");
    }
}
