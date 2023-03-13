package ProductivePeople.Repository;

import ProductivePeople.Model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalSpringDataJPA extends JpaRepository<Goal, Integer> {

    List<Goal> findByUserId(int id);

}
