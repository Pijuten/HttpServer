package at.fhtw.sampleapp.service.weather;

import at.fhtw.sampleapp.model.Weather;
import java.util.ArrayList;
import java.util.List;

public class WeatherDAL {
    private List<Weather> weatherData = new ArrayList();

    public WeatherDAL() {
        this.weatherData.add(new Weather(1, "Vienna", 9.0F));
        this.weatherData.add(new Weather(2, "Berlin", 8.0F));
        this.weatherData.add(new Weather(3, "Tokyo", 12.0F));
    }

    public Weather getWeather(Integer ID) {
        Weather foundWaether = (Weather)this.weatherData.stream().filter((waether) -> {
            return ID == waether.getId();
        }).findAny().orElse((Weather) null);
        return foundWaether;
    }

    public List<Weather> getWeather() {
        return this.weatherData;
    }

    public void addWeather(Weather weather) {
        this.weatherData.add(weather);
    }
}
