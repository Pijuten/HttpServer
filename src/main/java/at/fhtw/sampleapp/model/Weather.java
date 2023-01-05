package at.fhtw.sampleapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Weather {
    @JsonAlias({"id"})
    private Integer id;
    @JsonAlias({"city"})
    private String city;
    @JsonAlias({"temperature"})
    private float temperature;

    public Weather() {
    }

    public Weather(Integer id, String city, float temperature) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
