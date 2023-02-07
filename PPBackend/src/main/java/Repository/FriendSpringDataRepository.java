package Repository;

import Model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendSpringDataRepository extends JpaRepository<Friend, Integer> {
    Friend findById(int friendId);
}
