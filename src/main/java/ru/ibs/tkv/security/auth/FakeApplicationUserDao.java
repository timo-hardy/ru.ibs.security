package ru.ibs.tkv.security.auth;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ru.ibs.tkv.security.config.ApplicationUserRole.*;

@RequiredArgsConstructor
@Service("fake")
public class FakeApplicationUserDao implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectUserFromDbByUserNae(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser(
                        "oliver",
                        passwordEncoder.encode("password"),
                        EMPLOYEE.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "henry",
                        passwordEncoder.encode("password123"),
                        MANAGER.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "emma",
                        passwordEncoder.encode("password123"),
                        TRAINEE.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),

                new ApplicationUser(
                        "Vasya",
                        passwordEncoder.encode("vasya123"),
                        TRAINEE.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
    }
}

