package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.setContentType("text/html");

        PrintWriter out = null;
        try {
            out = response.getWriter(); // May throw IOException

            String ip = request.getRemoteAddr();
            String resolvedAddress = resolveAddress(ip); // Extracted method

            out.println("<h1>Client IP: " + ip + "</h1>");
            out.println("<h2>Resolved Address: " + resolvedAddress + "</h2>");
        } catch (IOException e) {
            // Properly handle IOException from getWriter()
            e.printStackTrace(); // Log the exception (consider using a logger in production)
        } finally {
            if (out != null) {
                out.close(); // Ensure the PrintWriter is closed to free resources
            }
        }
    }

    // Extracted method to resolve the address
    private String resolveAddress(String ip) {
        try {
            InetAddress addr = InetAddress.getByName(ip);
            return addr.getHostName();
        } catch (IOException e) {
            return "Unknown Host";
        }
    }
}


