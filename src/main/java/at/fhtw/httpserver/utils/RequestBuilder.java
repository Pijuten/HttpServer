package at.fhtw.httpserver.utils;

import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class RequestBuilder {
    public RequestBuilder() {
    }

    public Request buildRequest(BufferedReader bufferedReader) throws IOException {
        Request request = new Request();
        String line = bufferedReader.readLine();
        if (line != null) {
            String[] splitFirstLine = line.split(" ");
            request.setMethod(this.getMethod(splitFirstLine[0]));
            request.setUrlContent(splitFirstLine[1]);

            for(line = bufferedReader.readLine(); !line.isEmpty(); line = bufferedReader.readLine()) {
                request.getHeaderMap().ingest(line);
            }

            if (request.getHeaderMap().getContentLength() > 0) {
                char[] charBuffer = new char[request.getHeaderMap().getContentLength()];
                bufferedReader.read(charBuffer, 0, request.getHeaderMap().getContentLength());
                request.setBody(new String(charBuffer));
            }
        }

        return request;
    }

    private Method getMethod(String methodString) {
        return Method.valueOf(methodString.toUpperCase(Locale.ROOT));
    }
}
