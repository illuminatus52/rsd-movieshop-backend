package com.rsd_movieshop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsd_movieshop.dto.LoginRequest;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String ERROR_MESSAGE = "ERROR!";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String requestBody;

        try{
            requestBody = request.getReader().lines().collect(Collectors.joining());
            LoginRequest loginRequest = new ObjectMapper().readValue(requestBody, LoginRequest.class);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password);

            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }

    }
}
