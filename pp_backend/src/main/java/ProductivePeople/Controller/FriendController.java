package ProductivePeople.Controller;

import ProductivePeople.Model.Friend;
import ProductivePeople.Repository.FriendSpringDataJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/friends")
public class FriendController {

    private final FriendSpringDataJPA repository;

    public FriendController(FriendSpringDataJPA repository) {
        this.repository = repository;
    }

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

    //todo may need to add UserSpringDataJPA repository to associate the friend with their correct username
    @PostMapping("/add/{id}")
    public ResponseEntity<Friend> addFriend(@PathVariable int id) {
        Optional<Friend> friend = repository.findById(id);
        if(friend.get() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.save(friend.get());
        return new ResponseEntity<>(friend.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFriend(@PathVariable int id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
