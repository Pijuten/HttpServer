package at.fhtw.mtcg.service.user;

import at.fhtw.mtcg.model.User;

import java.util.List;
public class UserDAL {
    private List<User> userData;

    public UserDAL(){

    }
    public User getUser(Integer ID) {
        User foundUser = userData.stream()
                .filter(user -> ID == user.getId())
                .findAny()
                .orElse(null);

        return foundUser;
    }
    public List<User> getUser() {
        return userData;
    }

    public void addUser(User user) {
        userData.add(user);
    }
}
