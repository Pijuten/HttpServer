package at.fhtw.httpserver.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthTokenHandlerTest {
    @Test
    public void getNameTest(){
        AuthTokenHandler authTokenHandler = new AuthTokenHandler("Basic kienboec-mtcgToken");
        assertEquals("kienboec",authTokenHandler.getName());
    }
    @Test
    public void getNameEmptyStringTest(){
        AuthTokenHandler authTokenHandler = new AuthTokenHandler("");
        assertNull(authTokenHandler.getName());
    }
}