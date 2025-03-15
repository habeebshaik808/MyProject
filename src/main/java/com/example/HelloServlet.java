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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) { // AutoCloseable ensures proper resource handling
            String ip = request.getRemoteAddr();
            try {
                InetAddress addr = InetAddress.getByName(ip);
                out.println("<h1>Client IP: " + ip + "</h1>");
                out.println("<h2>Resolved Address: " + addr.getHostName() + "</h2>");
            } 
            catch (UnknownHostException uhex) {
                out.println("<h2>Error: Unable to resolve host</h2>");
            }
        } 
        catch (IOException ioex) {
            log("Error while writing response: " + ioex.getMessage(), ioex);
        }
    }
}

