package ProductivePeople.Security;

import ProductivePeople.Model.User;
import ProductivePeople.Repository.UserSpringDataJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//handles the register and authentication methods that the userController will use
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserSpringDataJPA repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConverter converter;
    private final AuthenticationManager manager;

    //creates a user, saves to database, and returns a generated token
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        repository.save(user);
        var jwtToken = converter.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        var user = repository.findByUsername(request.getUsername()).orElseThrow();

        var jwtToken = converter.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


}
