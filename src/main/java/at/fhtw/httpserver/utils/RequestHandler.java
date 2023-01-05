package at.fhtw.httpserver.utils;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private Socket clientSocket;
    private Router router;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public RequestHandler(Socket clientSocket, Router router) throws IOException {
        this.clientSocket = clientSocket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.printWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);
        this.router = router;
    }

    public void run() {
        try {
            Request request = (new RequestBuilder()).buildRequest(this.bufferedReader);
            Response response;
            if (request.getPathname() == null) {
                response = new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
            } else {
                response = this.router.resolve(request.getServiceRoute()).handleRequest(request);
            }

            this.printWriter.write(response.get());
        } catch (IOException var11) {
            PrintStream var10000 = System.err;
            String var10001 = Thread.currentThread().getName();
            var10000.println(var10001 + " Error: " + var11.getMessage());
        } finally {
            try {
                if (this.printWriter != null) {
                    this.printWriter.close();
                }

                if (this.bufferedReader != null) {
                    this.bufferedReader.close();
                    this.clientSocket.close();
                }
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }

    }
}
