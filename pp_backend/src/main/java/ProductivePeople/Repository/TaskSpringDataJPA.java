package ProductivePeople.Repository;

import ProductivePeople.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSpringDataJPA extends JpaRepository<Task, Integer> {

}
