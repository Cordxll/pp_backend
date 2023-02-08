package ProductivePeople.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FriendSpringDataJPATest {

    @Autowired
    FriendSpringDataJPA repository;

    @Test
    public void shouldRetrieveUserId() {
        assertEquals("bz", repository.findById(1).get().getUser().getUsername());
    }

}