package at.fhtw.mtcg;

import at.fhtw.httpserver.utils.Router;
import at.fhtw.httpserver.server.Server;
import at.fhtw.mtcg.service.login.LoginService;
import at.fhtw.mtcg.service.user.UserService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(10001, configureRouter());
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Router configureRouter()
    {
        Router router = new Router();
        router.addService("/users", new UserService());
        router.addService("/sessions", new LoginService());

        return router;
    }
}
