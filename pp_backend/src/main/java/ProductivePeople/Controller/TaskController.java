package ProductivePeople.Controller;

import ProductivePeople.Model.Task;
import ProductivePeople.Repository.TaskSpringDataJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskSpringDataJPA repository;

    public TaskController(TaskSpringDataJPA repository) {
        this.repository = repository;
    }

    @GetMapping
    public  List<Task> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable int id) {
        Optional<Task> task = repository.findById(id);
        if(task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> findByUserId(@PathVariable int id) {
        List<Task> tasks = repository.findByUserId(id);
//        if(task == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        repository.save(task);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Task task) {
        if(id != task.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        repository.save(task);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
