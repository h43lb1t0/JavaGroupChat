package network.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientImpl implements ClientInterface{

    private Socket clientSocket;

    public Socket connectToServer(String hostname, int port) throws IllegalArgumentException, IOException, UnknownHostException {
        this.clientSocket = new Socket(hostname, port);
        return this.clientSocket;
    }

    public Socket getClientSocket() {
        if (this.clientSocket == null) {
            throw new IllegalStateException("No client socket for this client found");
        }
        return this.clientSocket;
    }
}