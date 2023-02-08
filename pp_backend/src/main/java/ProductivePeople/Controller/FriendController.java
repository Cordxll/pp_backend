package ProductivePeople.Controller;

import ProductivePeople.Model.Friend;
import ProductivePeople.Repository.FriendSpringDataJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    private FriendSpringDataJPA repository;

    @GetMapping
    public List<Friend> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Friend> findByUsername(@PathVariable String username) {
        Friend friend = repository.findByUsername(username);
        if(friend == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(friend, HttpStatus.OK);
    }



}
