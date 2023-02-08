package ProductivePeople.Repository;

import ProductivePeople.Model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class TaskSpringDataJPATest {

    @Autowired
    TaskSpringDataJPA repository;

    @Test
    public void shouldReturnTask() {
        assertNotNull(repository.findById(1));
        Optional<Task> task = repository.findById(1);
        assertEquals("hello there", task.get().getDescription());
        System.out.println(task.get().getDescription());
    }


}