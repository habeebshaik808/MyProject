package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) { // AutoCloseable ensures proper resource handling
            String ip = request.getRemoteAddr();
            String resolvedAddress = resolveAddress(ip); // Extracted method

            out.println("<h1>Client IP: " + ip + "</h1>");
            out.println("<h2>Resolved Address: " + resolvedAddress + "</h2>");
        }
    }

    // Extracted method to resolve the address
    private String resolveAddress(String ip) {
        try {
            InetAddress addr = InetAddress.getByName(ip);
            return addr.getHostName();
        } catch (UnknownHostException e) {
            return "Unknown Host";
        }
    }
}


