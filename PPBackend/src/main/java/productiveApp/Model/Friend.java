package productiveApp.Model;

import jakarta.persistence.*;

@Entity
public class Friend {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private User user;

    public Friend(Integer id, User user) {
        this.id = id;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
