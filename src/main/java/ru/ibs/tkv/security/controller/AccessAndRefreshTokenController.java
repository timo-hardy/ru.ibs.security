package ru.ibs.tkv.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import ru.ibs.tkv.security.jwt.AccessAndRefreshTokens;
import ru.ibs.tkv.security.jwt.JwtProvider;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/update")
public class AccessAndRefreshTokenController {

    private final JwtProvider jwtProvider;
//    private final AccessAndRefreshTokens accessAndRefreshTokens;

    @GetMapping("/token")
    public String refreshTokens(Authentication authentication, HttpServletResponse response) throws IOException {
//        accessAndRefreshTokens.setAccessToken(jwtProvider.createToken(authentication));
//        accessAndRefreshTokens.setRefreshToken(jwtProvider.createRefreshToken(authentication));
        String accessToken = jwtProvider.createToken(authentication);
        String refreshToken = jwtProvider.createRefreshToken(authentication);
        response.setContentType("application/json");
        return accessToken + "\n" + refreshToken;
    }


}
