package ProductivePeople.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserSpringDataJPATest {

    @Autowired
    UserSpringDataJPA repository;

    @Test
    void findByUsername() {
        assertEquals("asdf", repository.findByUsername("bz").getPassword());
    }
}