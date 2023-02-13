package ProductivePeople.Repository;


import ProductivePeople.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpringDataJPA extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
