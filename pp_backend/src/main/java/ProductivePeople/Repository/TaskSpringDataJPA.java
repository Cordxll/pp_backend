package ProductivePeople.Repository;
import ProductivePeople.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskSpringDataJPA extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(int id);

}
