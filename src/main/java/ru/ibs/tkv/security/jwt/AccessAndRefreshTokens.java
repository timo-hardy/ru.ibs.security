package ru.ibs.tkv.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AccessAndRefreshTokens {
   String accessToken;
   String refreshToken;
}
