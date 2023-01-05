package at.fhtw.mtcg;

import at.fhtw.httpserver.server.Server;
import at.fhtw.httpserver.utils.Router;
import at.fhtw.mtcg.service.card.CardService;
import at.fhtw.mtcg.service.login.LoginService;
import at.fhtw.mtcg.service.packages.PackageService;
import at.fhtw.mtcg.service.transaction.TransactionService;
import at.fhtw.mtcg.service.user.UserService;
import java.io.IOException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Server server = new Server(10001, configureRouter());

        try {
            server.start();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private static Router configureRouter() {
        Router router = new Router();
        router.addService("/users", new UserService());
        router.addService("/sessions", new LoginService());
        router.addService("/packages", new PackageService());
        router.addService("/transactions", new TransactionService());
        router.addService("/cards", new CardService());
        return router;
    }
}
