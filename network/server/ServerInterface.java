package network.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public interface ServerInterface extends Runnable{

    /**
     * The server offers a connection for the client to connect
     * @return the server socket
     * @throws IllegalStateException when the port is already in use
     */
    Socket offerConnection() throws IllegalStateException, IOException;

    /**
     * Add a new socket to a list with all the sockets
     * @param socket the new socket
     * @throws IllegalArgumentException when the socket is null
     */
    void addConnectionToConnectionsList(Socket socket) throws IllegalArgumentException;

    /**
     * removes a socket from the list of sockets
     * @param socket the socket to remove
     * @throws IllegalArgumentException when the socket is not in the list
     */
    void removeConnectionFromConnectionsList(Socket socket) throws IllegalArgumentException;

    /**
     * When the Server socket exists its returned. When not, its created
     * @return the ServerSocket
     */
    ServerSocket getServerSocket();

    /**
     * get the last connect to the server
     * @return the socket of the latest connection to the server
     */
    Socket getLatestConnection();

    /**
     * return the list with all the clients connected to the server
     * @return list with Sockets of all clients connected to the server
     */
    ArrayList<Socket> getListOfConnections();

}
