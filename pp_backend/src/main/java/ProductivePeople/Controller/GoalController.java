package ProductivePeople.Controller;

import ProductivePeople.Model.Goal;
import ProductivePeople.Model.Task;
import ProductivePeople.Repository.GoalSpringDataJPA;
import ProductivePeople.Repository.TaskSpringDataJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalSpringDataJPA goalRepository;
    private final TaskSpringDataJPA taskRepository;

    public GoalController(GoalSpringDataJPA goalRepository, TaskSpringDataJPA taskRepository) {
        this.goalRepository = goalRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> findById(@PathVariable int id) {
        Optional<Goal> goal = goalRepository.findById(id);
        if(goal.get() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goal.get(), HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<List<Task>> getTasksById(@PathVariable int id) {
        Optional<Goal> goal = goalRepository.findById(id);
        List<Task> tasks = taskRepository.findAll().stream().filter(t -> t.getGoal().getId() == id).collect(Collectors.toList());
        if(goal.get() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Goal> addGoal(@RequestBody Goal goal) {
        goalRepository.save(goal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable int id, @RequestBody Goal goal) {
        if(id != goal.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        goalRepository.save(goal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGoalById(@PathVariable int id) {
        goalRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
