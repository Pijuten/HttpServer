package at.fhtw.mtcg.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class User {
    @JsonAlias({"Username"})
    private String username;
    @JsonAlias({"Password"})
    private String password;
    @JsonAlias({"Authtoken"})
    private String authtoken;
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.authtoken = null;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
