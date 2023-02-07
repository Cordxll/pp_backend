package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int userId;
    private String username;
    private String password;
    private String email;

    @OneToMany
    private List<Friend> friends = new ArrayList<>();
    @OneToMany
    private List<Task> tasks = new ArrayList<>();
    @OneToMany
    private List<Goal> goals = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void addFriends(Friend friend) {
        friends.add(friend);
    }

    public void removeFriends(Friend friend) {
        friends.remove(friend);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTasks(Task task) {
        tasks.add(task);
    }

    public void removeTasks(Task task){
        tasks.remove(task);
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void addGoals(Goal goal) {
        goals.add (goal);
    }

    public void removeGoals (Goal goal){
        goals.remove(goal);
    }
};