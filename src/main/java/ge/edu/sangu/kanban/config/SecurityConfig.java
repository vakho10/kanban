package ge.edu.sangu.kanban.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

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
    public JdbcUserDetailsManager userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}
