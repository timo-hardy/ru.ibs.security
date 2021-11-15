package ru.ibs.tkv.security.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> selectUserFromDbByUserNae(String username);
}
