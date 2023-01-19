package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public interface MsgHandlerInterface extends Runnable {

    /**
     * send a msg to the other chat partners
     * @param msg the message to send
     * @throws IOException when there're any conncetion problems
     */
    void sendMsg(Socket socket, String name, String msg) throws IOException;

    /**
     * Recives a message from and name from client
     * @param myDataInputStream the input stram for the clients socket
     * @param clientSocket clients socket
     * @throws IOException when there're any conncetion problems'
     */
    void receiveMsg(DataInputStream myDataInputStream, Socket clientSocket) throws IOException;

    /**
     * send a msg to every connected client exept the client who orinal sent this message
     * @param originSocket the socket of the client who sent this message
     * @param originName the name of the client who sent this message
     * @param msg the message to send
     * @throws IOException when there're any conncetion problems'
     */
    void sendMsgToEveryClientExeptTheoriginalSender(Socket originSocket, String originName, String msg) throws IOException;
}
