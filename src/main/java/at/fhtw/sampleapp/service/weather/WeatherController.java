package at.fhtw.sampleapp.service.weather;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public class WeatherController extends Controller {
    private WeatherDAL weatherDAL;

    public WeatherController(WeatherDAL weatherDAL) {
        this.weatherDAL = weatherDAL;
    }

    public Response getWeather(String id) {
        try {
            Weather weatherData = this.weatherDAL.getWeather(Integer.parseInt(id));
            String weatherDataJSON = this.getObjectMapper().writeValueAsString(weatherData);
            return new Response(HttpStatus.OK, ContentType.JSON, weatherDataJSON);
        } catch (JsonProcessingException var4) {
            var4.printStackTrace();
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Internal Server Error\" }");
        }
    }

    public Response getWeather() {
        try {
            List weatherData = this.weatherDAL.getWeather();
            String weatherDataJSON = this.getObjectMapper().writeValueAsString(weatherData);
            return new Response(HttpStatus.OK, ContentType.JSON, weatherDataJSON);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Internal Server Error\" }");
        }
    }

    public Response addWeather(Request request) {
        try {
            Weather weather = (Weather)this.getObjectMapper().readValue(request.getBody(), Weather.class);
            this.weatherDAL.addWeather(weather);
            return new Response(HttpStatus.CREATED, ContentType.JSON, "{ message: \"Success\" }");
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Internal Server Error\" }");
        }
    }
}
