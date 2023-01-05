//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package at.fhtw.httpserver.utils;

import at.fhtw.httpserver.server.Service;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<String, Service> serviceRegistry = new HashMap();

    public Router() {
    }

    public void addService(String route, Service service) {
        this.serviceRegistry.put(route, service);
    }

    public void removeService(String route) {
        this.serviceRegistry.remove(route);
    }

    public Service resolve(String route) {
        return (Service)this.serviceRegistry.get(route);
    }
}
