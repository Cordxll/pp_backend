package ProductivePeople.Repository;

import ProductivePeople.Model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalSpringDataJPA extends JpaRepository<Goal, Integer> {

}
