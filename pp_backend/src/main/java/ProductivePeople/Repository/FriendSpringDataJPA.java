package ProductivePeople.Repository;

import ProductivePeople.Model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendSpringDataJPA extends JpaRepository<Friend, Integer> {

    Friend findByUsername(String username);

}
