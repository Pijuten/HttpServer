package at.fhtw.httpserver.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthTokenHandler {
    private String AuthToken;

    public AuthTokenHandler(String AuthToken) {
        this.AuthToken = AuthToken;
    }

    public String getName() {
        try {
            Pattern p = Pattern.compile("Basic (.*?)-mtcgToken");
            Matcher m = p.matcher(this.AuthToken);
            m.find();
            return m.group(1);
        }catch (Exception e){
            return null;
        }

    }
}
