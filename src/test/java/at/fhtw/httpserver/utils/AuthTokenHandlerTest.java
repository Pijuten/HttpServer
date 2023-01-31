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
    @Test
    public void creatTokenTest(){
        AuthTokenHandler authTokenHandler = new AuthTokenHandler("kienboec");
        assertEquals(authTokenHandler.createToken(),"Basic kienboec-mtcgToken");
    }
    @Test
    public void compareToktenTest(){
        AuthTokenHandler authTokenHandler = new AuthTokenHandler("Basic kienboec-mtcgToken");
        assertEquals(authTokenHandler.compareToken(),"kienboec");
    }
}