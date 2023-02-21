package ProductivePeople.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtRequestFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    //specifies what paths are available and who has access to the paths
    //todo provide a .permitAll() for the home and login page [use .requestMatchers()]

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers( "/demo/hello", "/user/*", "/user/login/authenticate", "/user/register/authenticate", "/goals").permitAll()
                .requestMatchers("/demo/auth/hello").hasAnyAuthority("USER")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
//                .addFilter(jwtAuthFilter);
                //says that you want the jwtAuthFilter to be used before the UsernamePassword filter to be used
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}