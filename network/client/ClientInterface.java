package network.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public interface ClientInterface {

    /**
     * creates a new connection to the server
     * @param hostname the host to connect to
     * @param port the port to connect to
     * @throws IllegalArgumentException when the port is invalid
     * @throws IOException when any network error occurs
     */
    Socket connectToServer(String hostname, int port) throws IllegalArgumentException, IOException, UnknownHostException;

    /**
     * get the socket of the client
     * @return the socket of the client
     * @throws IllegalStateException if no socket is available
     */
    Socket getClientSocket() throws IllegalStateException;
}
