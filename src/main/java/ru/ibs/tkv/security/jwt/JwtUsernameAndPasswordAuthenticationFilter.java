package ru.ibs.tkv.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernamePasswordAuthRequest usernamePasswordAuthRequest =
                    new ObjectMapper().readValue(request.getInputStream(), UsernamePasswordAuthRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(usernamePasswordAuthRequest.getUsername(), usernamePasswordAuthRequest.getPassword());

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            log.error("Unexpected error", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String accsess_token = jwtProvider.createToken(authResult);
//        String refresh_token = jwtProvider.createToken(authResult);
//        response.addHeader(HttpHeaders.AUTHORIZATION, accsess_token);
//        response.setContentType("application/json");
        AccessAndRefreshTokens accessAndRefreshTokens = new AccessAndRefreshTokens();
        accessAndRefreshTokens.setAccessToken(jwtProvider.createRefreshToken(authResult));
        accessAndRefreshTokens.setRefreshToken(jwtProvider.createRefreshToken(authResult));
        response.setContentType("application/json");
        String tokens = new ObjectMapper().writeValueAsString(accessAndRefreshTokens);
        PrintWriter printWriter = response.getWriter();
        printWriter.println(tokens);
        printWriter.flush();
    }
}
