
package at.fhtw.httpserver.http;

public enum ContentType {
    PLAIN_TEXT("text/plain"),
    HTML("text/html"),
    JSON("application/json");

    public final String type;

    private ContentType(String type) {
        this.type = type;
    }
}
