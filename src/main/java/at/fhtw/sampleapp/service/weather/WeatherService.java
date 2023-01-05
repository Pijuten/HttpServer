package at.fhtw.sampleapp.service.weather;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class WeatherService implements Service {
    private final WeatherController weatherController = new WeatherController(new WeatherDAL());

    public WeatherService() {
    }

    public Response handleRequest(Request request) {
        if (request.getMethod() == Method.GET && request.getPathParts().size() > 1) {
            return this.weatherController.getWeather((String)request.getPathParts().get(1));
        } else if (request.getMethod() == Method.GET) {
            return this.weatherController.getWeather();
        } else {
            return request.getMethod() == Method.POST ? this.weatherController.addWeather(request) : new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
        }
    }
}
