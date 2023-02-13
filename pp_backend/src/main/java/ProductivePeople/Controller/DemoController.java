package ProductivePeople.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello!", HttpStatus.OK);
    }

    @GetMapping("/auth/hello")
    public ResponseEntity<String> authenticatedHello() {
        return new ResponseEntity<>("Hello! You have a token!", HttpStatus.OK);
    }

}
