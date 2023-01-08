package at.fhtw.httpserver.server;

import at.fhtw.httpserver.http.Method;
import java.util.ArrayList;
import java.util.List;

public class Request {
    private Method method;
    private String urlContent;
    private String pathname;
    private List<String> pathParts;
    private String params;
    private HeaderMap headerMap = new HeaderMap();
    private String body;

    public Request() {
    }

    public String getServiceRoute() {
        return this.pathParts != null && !this.pathParts.isEmpty() ? "/" + this.pathParts.get(0) : null;
    }

    public String getUrlContent() {
        return this.urlContent;
    }

    public void setUrlContent(String urlContent) {
        this.urlContent = urlContent;
        Boolean hasParams = urlContent.indexOf("?") != -1;
        if (hasParams) {
            String[] pathParts = urlContent.split("\\?");
            this.setPathname(pathParts[0]);
            this.setParams(pathParts[1]);
        } else {
            this.setPathname(urlContent);
            this.setParams(null);
        }

    }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getPathname() {
        return this.pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
        String[] stringParts = pathname.split("/");
        this.pathParts = new ArrayList();
        String[] var3 = stringParts;
        int var4 = stringParts.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String part = var3[var5];
            if (part != null && part.length() > 0) {
                this.pathParts.add(part);
            }
        }

    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public HeaderMap getHeaderMap() {
        return this.headerMap;
    }

    public void setHeaderMap(HeaderMap headerMap) {
        this.headerMap = headerMap;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getPathParts() {
        return this.pathParts;
    }

    public void setPathParts(List<String> pathParts) {
        this.pathParts = pathParts;
    }
}
