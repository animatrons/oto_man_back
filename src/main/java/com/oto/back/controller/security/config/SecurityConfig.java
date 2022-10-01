package com.oto.back.controller.security.config;

import com.oto.back.app.AuthUserDetailsService;
import com.oto.back.app.UserApp;
import com.oto.back.controller.security.AuthSuccessHandler;
import com.oto.back.controller.security.filter.BasicJwtAuthenticationFilter;
import com.oto.back.controller.security.filter.UsernamePasswordJwtAuthenticationFilter;
import com.oto.back.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthUserDetailsService authUserDetailsService;
    private final String secret;

    public SecurityConfig(AuthenticationManager authenticationManager, AuthSuccessHandler authSuccessHandler, AuthUserDetailsService authUserDetailsService, @Value("${jwt.secret}") String secret) {
        this.authenticationManager = authenticationManager;
        this.authSuccessHandler = authSuccessHandler;
        this.authUserDetailsService = authUserDetailsService;
        this.secret = secret;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests((auth) -> {
                    try {
                        auth
                                .antMatchers(HttpMethod.POST, "/auth/api/autos", "/auth/api/rides", "/auth/api/riders").hasRole(Role.ADMIN.name())
                                .antMatchers(HttpMethod.POST, "/auth/api/users").hasRole(Role.SUPER.name())
                                .antMatchers(HttpMethod.PUT, "/auth/api/autos", "/auth/api/rides", "/auth/api/riders").hasRole(Role.ADMIN.name())
                                .antMatchers(HttpMethod.PUT, "/auth/api/users").hasRole(Role.SUPER.name())
                                .antMatchers(HttpMethod.DELETE, "/auth/api/autos", "/auth/api/rides", "/auth/api/riders").hasRole(Role.ADMIN.name())
                                .antMatchers(HttpMethod.DELETE, "/auth/api/users").hasRole(Role.SUPER.name())
                                .antMatchers("/auth/api/rides").hasRole(Role.ADMIN.name())
                                .anyRequest().permitAll()
                                .and()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .addFilter(authenticationFilter())
                                .addFilter(new BasicJwtAuthenticationFilter(authenticationManager, authUserDetailsService, secret))
                                .exceptionHandling()
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UsernamePasswordJwtAuthenticationFilter authenticationFilter() {
        UsernamePasswordJwtAuthenticationFilter filter = new UsernamePasswordJwtAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}
