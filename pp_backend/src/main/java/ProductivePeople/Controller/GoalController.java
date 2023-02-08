package ProductivePeople.Controller;

import ProductivePeople.Model.Goal;
import ProductivePeople.Repository.GoalSpringDataJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private GoalSpringDataJPA repository;

    @GetMapping
    public List<Goal> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> findById(@PathVariable int id) {
        Optional<Goal> goal = repository.findById(id);
        if(goal.get() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goal.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Goal> addGoal(@RequestBody Goal goal) {
        repository.save(goal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable int id, @RequestBody Goal goal) {
        if(id != goal.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        repository.save(goal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGoalById(@PathVariable int id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
