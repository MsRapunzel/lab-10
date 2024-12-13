package com.msrapunzel;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/receive-message")
public class ReceiveMessageServlet extends HttpServlet {

    @Inject
    private MessageReceiverBean messageReceiverBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String messageSelector = request.getParameter("messageSelector");

        messageReceiverBean.receiveMessageWithSelector(messageSelector);

        response.sendRedirect("ReceiveMessage.jsp");
    }
}
