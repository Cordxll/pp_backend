package ProductivePeople.Controller;

import ProductivePeople.Model.User;
import ProductivePeople.Repository.UserSpringDataJPA;
import ProductivePeople.Security.*;
import ProductivePeople.Service.Result;
import ProductivePeople.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserSpringDataJPA repository;

    //handles the register and authenticate methods (grants tokens)
    private final AuthenticationService authService;
    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        User user = repository.findByUsername(username).orElse(null);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UpdateRequest request) {

        Result result = userService.update(request);
        System.out.println(result.getMessages());
        if(result.getMessages().size() == 0) {
            result.success = true;
        }
        if(!result.success) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.CONFLICT);
        }


        return ResponseEntity.ok(authService.update(request));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register/authenticate")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Result result = userService.create(request);
        if(result.getMessages().size() == 0) {
            result.success = true;
        }
        if(!result.success) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        Result result = userService.login(request);
        if(result.getMessages().size() == 0) {
            result.success = true;
        }
        if(!result.success) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
