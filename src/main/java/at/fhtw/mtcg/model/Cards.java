package at.fhtw.mtcg.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Cards {
    @JsonAlias({"Id"})
    private String id;

    @JsonAlias({"Name"})
    private String name;

    @JsonAlias({"Damage"})
    private float damage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}