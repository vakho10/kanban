package ge.edu.sangu.kanban.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/profile/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        // When the login page is specified in the Spring Security configuration,
                        // you are responsible for rendering the page!
                        .loginPage("/login")
                        .defaultSuccessUrl("/profile") // Where user will be redirected after authenticating
                        .permitAll() // Already permitted by our design
                )
                .logout(Customizer.withDefaults())
                .rememberMe(configurer -> configurer
                        .key("superRandomSecretKey")
                        .tokenValiditySeconds(86400) // 1 day in seconds (default is 14 days)
                );

        http.csrf(customizer -> customizer
                // Only ignore CSRF for "/h2-console/**" endpoints
                .ignoringRequestMatchers("/h2-console/**")
        );

        // Allow frames for the H2 console (required to display H2's console in a browser)
        http.headers(customizer -> customizer
                .frameOptions(Customizer.withDefaults())
                .disable()
        );

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        // Admin
        userDetailsManager.createUser(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build());
        // Simple users
        userDetailsManager.createUser(User.builder()
                .username("vakho")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build());
        userDetailsManager.createUser(User.builder()
                .username("luka")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build());
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}
