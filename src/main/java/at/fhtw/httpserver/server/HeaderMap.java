package at.fhtw.httpserver.server;

import java.util.HashMap;
import java.util.Map;

public class HeaderMap {
    public static final String CONTENT_LENGTH_HEADER = "Content-Length";
    public static final String HEADER_NAME_VALUE_SEPARATOR = ":";
    private Map<String, String> headers = new HashMap();

    public HeaderMap() {
    }

    public void ingest(String headerLine) {
        String[] split = headerLine.split(":", 2);
        this.headers.put(split[0], split[1].trim());
    }

    public String getHeader(String headerName) {
        return (String)this.headers.get(headerName);
    }

    public int getContentLength() {
        String header = (String)this.headers.get("Content-Length");
        return header == null ? 0 : Integer.parseInt(header);
    }

    public void print() {
        System.out.println(this.headers);
    }
}
