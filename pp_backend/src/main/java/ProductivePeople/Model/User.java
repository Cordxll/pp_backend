package ProductivePeople.Model;

import ProductivePeople.Security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String color;
    private String pictureUrl;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<Friend> friends = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Goal> goals = new ArrayList<>();


    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> listRole = new ArrayList<GrantedAuthority>();
        if(role != null){
        listRole.add(new SimpleGrantedAuthority(role.name()));
        return listRole;}
        else{
        return Collections.emptyList();}
//        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    //    public List<Friend> getFriends() {
//        return friends;
//    }
//
//    public void addFriends(Friend friend) {
//        friends.add(friend);
//    }
//
//    public void removeFriends(Friend friend) {
//        friends.remove(friend);
//    }
//
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getColor(), user.getColor()) && getRole() == user.getRole() && Objects.equals(getFriends(), user.getFriends()) && Objects.equals(getTasks(), user.getTasks()) && Objects.equals(getGoals(), user.getGoals());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEmail(), getColor(), getRole(), getFriends(), getTasks(), getGoals());
    }
};