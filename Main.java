import java.io.IOException;
import java.net.Socket;

import UI.*;
import network.*;
import network.client.ClientImpl;
import network.client.ClientInterface;
import network.client.ClientMsgHanderInterface;
import network.client.ClientMsgHandlerImpl;
import network.server.ServerImpl;
import network.server.ServerInterface;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterfaceImpl();
        MsgHandlerInterface msghandlerServer = null;

        ui.welcomeMsg();
        String username = ui.askForUserName();
        boolean isServer = ui.askIfWantsToBeServer();

        if (isServer) {
            System.out.println("is server");
            ServerInterface server = new ServerImpl(4242);
            Thread serverThread = new Thread(server);
            serverThread.start();
            msghandlerServer = new MsgHandlerImpl(server);
            Thread msgHandlerServerThread = new Thread(msghandlerServer);
            msgHandlerServerThread.start();
            while (true) {
                try {
                    msghandlerServer.sendMsgToEveryClientExeptTheoriginalSender(null, username, ui.readChatMsgToBeSent());       
                } catch (Exception e) {
                    // ignore
                }
                     
            }

        } else {
            ClientInterface client = new ClientImpl();

            try {
                Socket clientSocket = client.connectToServer("localhost", 4242);
                MsgHandlerInterface mmsgHandler = new MsgHandlerImpl();
                ClientMsgHanderInterface clientMsgHandler = new ClientMsgHandlerImpl(clientSocket);
                Thread clientMsgHandlerThread = new Thread(clientMsgHandler);
                clientMsgHandlerThread.start();

                while (true) {
                    mmsgHandler.sendMsg(clientSocket, username, ui.readChatMsgToBeSent());
                }
            } catch (IOException e) {
                System.out.println("foo");
            }
        }
        


    }
}
