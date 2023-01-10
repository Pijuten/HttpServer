package at.fhtw.mtcg.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class UserData {
    @JsonAlias({"Bio"})
    private String bio;

    @JsonAlias({"Image"})
    private String image;

    @JsonAlias({"Name"})
    private String name;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
