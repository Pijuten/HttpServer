package at.fhtw.mtcg.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Controller() {
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }
}
