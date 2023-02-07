//package Model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import java.time.LocalDateTime;
//
//@Entity
//public class Task {
//
//    @Id
//    @GeneratedValue
//    private int id;
//    private String title;
//    private String description;
//    private LocalDateTime time;
//    private boolean isComplete;
//    private boolean isPublic;
//    @ManyToOne
//    private User user;
//
//    public Task(int id, String title, String description, LocalDateTime time, boolean isComplete, boolean isPublic, User user) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.time = time;
//        this.isComplete = isComplete;
//        this.isPublic = isPublic;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public LocalDateTime getTime() {
//        return time;
//    }
//
//    public void setTime(LocalDateTime time) {
//        this.time = time;
//    }
//
//    public boolean isComplete() {
//        return isComplete;
//    }
//
//    public void setComplete(boolean complete) {
//        isComplete = complete;
//    }
//
//    public boolean isPublic() {
//        return isPublic;
//    }
//
//    public void setPublic(boolean aPublic) {
//        isPublic = aPublic;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
