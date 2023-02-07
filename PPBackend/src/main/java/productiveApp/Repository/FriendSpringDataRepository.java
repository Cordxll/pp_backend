package productiveApp.Repository;

import productiveApp.Model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FriendSpringDataRepository extends JpaRepository<Friend, Integer> {
    Friend findById(int friendId);
}
