package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientInfoServlet {

    private static final Logger LOGGER = Logger.getLogger(ClientInfoServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter(); // May throw IOException
            String ip = request.getRemoteAddr();
            String resolvedAddress = resolveAddress(ip); // Extracted method

            out.println("<h1>Client IP: " + ip + "</h1>");
            out.println("<h2>Resolved Address: " + resolvedAddress + "</h2>");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error obtaining response writer", e);
        } finally {
            if (out != null) {
                out.close(); // Ensure PrintWriter is closed properly
            }
        }
    }

    private String resolveAddress(String ip) {
        // Dummy method, replace with actual logic if needed
        return "Resolved: " + ip;
    }
}


