package ProductivePeople.Repository;

import ProductivePeople.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpringDataJPA extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
