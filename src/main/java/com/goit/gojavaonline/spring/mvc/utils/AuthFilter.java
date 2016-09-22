package com.goit.gojavaonline.spring.mvc.utils;

import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class AuthFilter implements Filter {

    private static final String ACCESS_DENIED_ERROR_JSON = "{\"authFailed\": true; \"message\":\"Access denied.\"}";
    private static final String ACCESS_DENIED_ERROR_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                                                          "<model>" +
                                                          "<message>Access denied.</message>" +
                                                          "<authFailed>true</authFailed>" +
                                                          "</model>";

    @Override
    public void init( FilterConfig filterConfig )
            throws ServletException {
        System.out.println("Initializing AuthFilter.");
    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        AuthTokenRepository.initializeAuthAccess( httpServletRequest );
        try {
            filterChain.doFilter( servletRequest, servletResponse );
        } catch(UserIsNotAuthenticatedException e) {
            processUnauthenticatedAccess(httpServletRequest, (HttpServletResponse) servletResponse);
        } finally {
            AuthTokenRepository.releaseCurrentToken();
        }
    }

    private void processUnauthenticatedAccess( HttpServletRequest request, HttpServletResponse response) {
        if (response.isCommitted()) {
            System.err.println("Failed to populate \"Access denied.\" response since http response is in \"committed\" state.");
        } else {
            String contentType = getContentType(request);
            try {
                if ( MediaType.APPLICATION_JSON_VALUE.equals( contentType )) {
                    prepareTypedResponse(contentType, ACCESS_DENIED_ERROR_JSON, response);
                } else if (MediaType.APPLICATION_XML_VALUE.equals( contentType )) {
                    prepareTypedResponse(contentType, ACCESS_DENIED_ERROR_XML, response);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for guest user.");
                }
            } catch ( IOException e ) {
                System.err.println("Failed to send \"Access denied.\" response back to request client.");
                e.printStackTrace(System.err);
            }
        }
    }

    private String getContentType(HttpServletRequest request) {
        String acceptHeaderValue = null;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.equalsIgnoreCase("accept")) {
                acceptHeaderValue = request.getHeader( headerName );
            }
        }
        return acceptHeaderValue;
    }

    private void prepareTypedResponse(String contentType, String content, HttpServletResponse response) throws IOException {
        response.setContentType(contentType);
        response.setStatus( HttpServletResponse.SC_FORBIDDEN );
        ServletOutputStream out = response.getOutputStream();
        out.println(content);
        out.close();
    }

    @Override
    public void destroy() {
        System.out.println("Releasing AuthFilter.");
    }
}