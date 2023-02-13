package ProductivePeople.Controller;

import ProductivePeople.Model.User;
import ProductivePeople.Repository.UserSpringDataJPA;
import ProductivePeople.Security.AuthenticationRequest;
import ProductivePeople.Security.AuthenticationResponse;
import ProductivePeople.Security.AuthenticationService;
import ProductivePeople.Security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserSpringDataJPA repository;

    //handles the register and authenticate methods (grants tokens)
    private final AuthenticationService service;


    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        User user = repository.findByUsername(username).get();
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        if(id != user.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        repository.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }


//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody Map<String, String> credentials) {
//        User user = new User();
//        user.setUsername(credentials.get("username"));
//        user.setPassword(credentials.get(encoder.encode(credentials.get("password"))));
//        user.setEmail(credentials.get("email"));
//        repository.save(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }

}
