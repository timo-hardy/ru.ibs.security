package ru.ibs.tkv.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsernamePasswordRequest {
    private String username;
    private String password;
}
