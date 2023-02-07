package productiveApp.Model;

import jakarta.persistence.*;

@Entity
public class Friend {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User user;

    public Friend(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
