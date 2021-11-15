package ru.ibs.tkv.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsernamePasswordAuthRequest {

    private String username;
    private String password;
}
