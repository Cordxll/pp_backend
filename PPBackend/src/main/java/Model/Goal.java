package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Goal {

    @Id
    @GeneratedValue
    private int goalId;
    private String title;
    private int progress;
    private Date timeline;
    private boolean isComplete;
    @ManyToOne
    private User user;

    public Goal(int goalId, String title, int progress, Date timeline, boolean isComplete, User user) {
        this.goalId = goalId;
        this.title = title;
        this.progress = progress;
        this.timeline = timeline;
        this.isComplete = isComplete;
        this.user = user;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Date getTimeline() {
        return timeline;
    }

    public void setTimeline(Date timeline) {
        this.timeline = timeline;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
