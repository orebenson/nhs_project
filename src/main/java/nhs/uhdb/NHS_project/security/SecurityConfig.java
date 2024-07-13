package nhs.uhdb.NHS_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcUserDetails = new JdbcDaoImpl();
        jdbcUserDetails.setDataSource(dataSource);
        jdbcUserDetails.setUsersByUsernameQuery("select email as username, password, enabled from user_table where email=?");
        jdbcUserDetails.setAuthoritiesByUsernameQuery("select u.email as username, a.authority from user_table u inner join user_authorities a on u.user_id = a.user_id and u.email=?");
        return jdbcUserDetails;
    }

    public static final String[] ENDPOINTS_WHITELIST = {
            // all users can see all pages currently, for testing purposes
//            "/**",
            //
            "/",
            "/css/**",
            "/js/**",
            "/assets/**",
            "/403",
            "/login",
            "/login/**",
            "/register",
            "/register/**",
            "/account",
            "/account/**",
            "/api/**"
    };

    public static final String[] USER_WHITELIST = {
            "/logout",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                        .antMatchers(ENDPOINTS_WHITELIST).permitAll()
                        .antMatchers(USER_WHITELIST).hasRole("USER")
                        .anyRequest().hasRole("ADMIN"))
//                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .permitAll()
                        .defaultSuccessUrl("/login/success")
                        .failureUrl("/login/error"))
                .logout((l) -> l
                        .permitAll()
                        .logoutSuccessUrl("/"))
                .exceptionHandling()
                .accessDeniedPage("/403");

        return http.build();
    }


}