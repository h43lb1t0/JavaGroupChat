package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import UI.*;
import network.server.ServerInterface;

public class MsgHandlerImpl implements MsgHandlerInterface{

    //DataInputStream dais = new DataInputStream(System.in);
    //DataOutputStream daos  = new DataOutputStream(System.out);
    ServerInterface server;
    DataInputStream dais;
    DataOutputStream daos;
    int readerThreadCount = 0;
    UserInterface ui = new UserInterfaceImpl();

    public MsgHandlerImpl(ServerInterface server) {
        this.server = server;
    }

    public MsgHandlerImpl() {
        this(null);
    }

    public void sendMsg(Socket socket, String name, String msg) throws IOException {
        this.daos = new DataOutputStream(socket.getOutputStream());
        daos.writeUTF(name);
        daos.writeUTF(msg);
    }

    public void sendMsgToEveryClientExeptTheoriginalSender(Socket originSocket, String originName, String msg) throws IOException {
        for (Socket reciverSocket : this.server.getListOfConnections()) {
            if (originSocket != reciverSocket) {
                sendMsg(reciverSocket, originName, msg);
            }
        }
    }

    public String[] receiveMsg() {
        return new String[2];
    }

    public void run() {
        this.readerThreadCount++;
        System.out.println("Msg reader thread" + this.readerThreadCount + " is running");
    
        Socket clientSocket = server.getLatestConnection();
        DataInputStream myDataInputStream = null;
        try {
            myDataInputStream = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error in msg handler: " + e.getMessage());
        }
        while (true) {
            try {
                String senderName = myDataInputStream.readUTF();
                String senderMsg = myDataInputStream.readUTF();

                ui.displayChatMsg(senderName, senderMsg);
                this.sendMsgToEveryClientExeptTheoriginalSender(clientSocket, senderName, senderMsg);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                try {
                    clientSocket.close();   
                } catch (Exception expt) {
                    server.removeConnectionFromConnectionsList(clientSocket);
                }
                break;
            }
        }

    
    }
}
