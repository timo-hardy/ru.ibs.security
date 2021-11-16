package ru.ibs.tkv.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
public class AccessAndRefreshTokens {
   String accessToken;
   String refreshToken;
}
