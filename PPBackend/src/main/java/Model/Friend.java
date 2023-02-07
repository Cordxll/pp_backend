package Model;

import javax.persistence.*;

@Entity
public class Friend {

    @Id
    @GeneratedValue
    private int friendId;
    @ManyToOne
    private int userId;

    public Friend(int friendId, int userId) {
        this.friendId = friendId;
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
