package ru.ibs.tkv.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ru.ibs.tkv.security.auth.ApplicationUser;
import ru.ibs.tkv.security.auth.ApplicationUserService;
import ru.ibs.tkv.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;

import static ru.ibs.tkv.security.config.ApplicationUserPermission.TASK_WRITE;
import static ru.ibs.tkv.security.config.ApplicationUserRole.*;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
//                .antMatchers("/manager/api/**").hasRole(MANAGER.name())
//                .antMatchers(HttpMethod.PUT,"/api/task/**").hasAnyAuthority(TASK_WRITE.getPermission())
//                .antMatchers("/api/task/**").hasAnyRole(EMPLOYEE.name(), TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    //    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails oliverUser = User.builder()
//                .username("oliver")
//                .password(passwordEncoder.encode("password"))
//                .authorities(EMPLOYEE.getAuthorities())
//                .build();
//
//        UserDetails henryUser = User.builder()
//                .username("henry")
//                .password(passwordEncoder.encode("password123"))
//                .authorities(MANAGER.getAuthorities())
//                .build();
//
//        UserDetails emmaUser = User.builder()
//                .username("emma")
//                .password(passwordEncoder.encode("password1"))
//                .authorities(TRAINEE.getAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(oliverUser, henryUser, emmaUser);
//    }
}
