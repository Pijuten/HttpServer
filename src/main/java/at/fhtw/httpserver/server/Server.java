package at.fhtw.httpserver.server;

import at.fhtw.httpserver.utils.RequestHandler;
import at.fhtw.httpserver.utils.Router;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private Router router;

    public Server(int port, Router router) {
        this.port = port;
        this.router = router;
    }

    public void start() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println("Start http-server...");
        System.out.println("http-server running at: http://localhost:" + this.port);
        ServerSocket serverSocket = new ServerSocket(this.port);

        try {
            while(true) {
                Socket clientConnection = serverSocket.accept();
                RequestHandler socketHandler = new RequestHandler(clientConnection, this.router);
                executorService.submit(socketHandler);
            }
        } catch (Throwable var6) {
            try {
                serverSocket.close();
            } catch (Throwable var5) {
                var6.addSuppressed(var5);
            }

            throw var6;
        }
    }
}
