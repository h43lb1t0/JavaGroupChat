package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import network.MsgHandlerImpl;
import network.MsgHandlerInterface;

public class ServerImpl implements ServerInterface {

    ArrayList<Socket> sockets = new ArrayList<Socket>();
    ServerSocket server;
    private final int PORT;

    public ServerImpl(int port) {
        if (port > 1 && port < 65535) {
            this.PORT = port;
        } else {
            throw new IllegalArgumentException("port must be between 1 and 65535");
        }
    }

    public Socket offerConnection() throws IOException{
        System.out.println("offerconnect wurde erreicht");
        ServerSocket me = this.getServerSocket();
        Socket socket = me.accept();
        return socket;
    }

    public ServerSocket getServerSocket() {
        System.out.println("get server socket wurde erreicht");
        if (this.server == null) {
            try {
                this.server = new ServerSocket(this.PORT);
            } catch (IOException e) {
                System.out.println("couldn't create new server socket");
            }            
        }

        return this.server;
    }

    public void addConnectionToConnectionsList(Socket socket) {
        sockets.add(socket);
    }

    public void removeConnectionFromConnectionsList(Socket socket) {
        sockets.remove(socket);
    }

    public Socket getLatestConnection() {
        if (sockets.isEmpty()) {
            throw new IllegalStateException("noconnection yet");
        }
        return sockets.get(sockets.size() - 1);
    }

    public ArrayList<Socket> getListOfConnections() {
        return this.sockets;
    }

    public void run() {
        MsgHandlerInterface msgHandler = new MsgHandlerImpl(this);
        while (true) {
            try {
                Socket mySocket = offerConnection();
                addConnectionToConnectionsList(mySocket);
                System.out.println("[Server log]: new connection established");

                Thread readClientMsgThread = new Thread(msgHandler);
                readClientMsgThread.start();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
