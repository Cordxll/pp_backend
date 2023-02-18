package ProductivePeople.Service;

import ProductivePeople.Model.User;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private final ArrayList<String> messages = new ArrayList<>();

    private User payload;

    public boolean success;

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public User getPayload() {
        return payload;
    }

    public void setPayload(User payload) {
        this.payload = payload;
    }

}
