package com.openclassrooms.projet9microservicegateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;

import java.net.URI;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public ServerAuthenticationSuccessHandler authenticationSuccessHandler() {
        RedirectServerAuthenticationSuccessHandler handler = new RedirectServerAuthenticationSuccessHandler();
        handler.setLocation(URI.create("/ui/patient/"));
        return handler;
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {


        http.authorizeExchange((authorize) ->
                        authorize
                                .anyExchange().authenticated()


                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> {
                    form.authenticationSuccessHandler(authenticationSuccessHandler());
                })
                .logout(
                        Customizer.withDefaults()
                ).csrf(ServerHttpSecurity.CsrfSpec::disable);


        return http.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {

        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("user")).build();

        return new MapReactiveUserDetailsService(user);
    }


}
