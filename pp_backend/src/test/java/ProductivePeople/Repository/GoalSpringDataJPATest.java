package ProductivePeople.Repository;

import ProductivePeople.Model.Goal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class GoalSpringDataJPATest {

    @Autowired
    GoalSpringDataJPA repository;

    @Autowired
    UserSpringDataJPA userRepository;

    @Test
    public void shouldGetTitle() {
        assertEquals("weight loss", repository.findById(1).get().getTitle());
    }

    @Test
    @DirtiesContext
    public void shouldAddNewGoal() {
        Goal goal = new Goal();
        goal.setTitle("finish project");
        goal.setProgress(20);
        goal.setComplete(false);
        goal.setTimeline(LocalDate.now());

        repository.save(goal);

        assertEquals("bz", repository.findById(1));

    }

}