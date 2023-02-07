package Model;

import java.util.Date;

public class Goal {

    private int goalId;
    private String title;
    private int progress;
    private Date timeline;
    private boolean isComplete;

    public Goal(int goalId, String title, int progress, Date timeline, boolean isComplete) {
        this.goalId = goalId;
        this.title = title;
        this.progress = progress;
        this.timeline = timeline;
        this.isComplete = isComplete;
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
}
