package Model;

public class Task {

    private int taskId;
    private String title;
    private String description;
    private int time;
    private boolean isComplete;
    private boolean isPublic;

    public Task(int taskId, String title, String description, int time, boolean isComplete, boolean isPublic) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.time = time;
        this.isComplete = isComplete;
        this.isPublic = isPublic;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
