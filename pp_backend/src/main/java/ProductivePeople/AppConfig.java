package ProductivePeople;

import ProductivePeople.Repository.UserSpringDataJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//annotating with an @Configuration will allow Spring to identify this class as a file that has particular settings for the application as a whole
//or it will specify what classes/interfaces that methods from other classes should use when specifying these classes/interfaces
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserSpringDataJPA repository;

    //encrypts a user's password to ensure their actual password is not stored in the database
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //fetches a user from the database - the user will already have the necessary details to check for claims or assign a token to a valid user
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    //fetches user details and encodes passwords
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("DELETE", "GET", "POST", "PUT", "OPTIONS")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*");
            }
        };
    }

}
