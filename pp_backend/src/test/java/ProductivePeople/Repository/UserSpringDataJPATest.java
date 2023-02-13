package ProductivePeople.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserSpringDataJPATest {

    @Autowired
    UserSpringDataJPA repository;

    @Test
    void findByUsername() {
        System.out.println(repository.findByUsername("bz").orElse(null));
        System.out.println(Arrays.toString(new List[]{repository.findAll()}));


//        assertEquals("asdf", repository.findByUsername("bz").get().getPassword());
    }
}