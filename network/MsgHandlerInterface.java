package network;

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
     * recives a message and the name of the chat partner who sent this message
     * @return String Array with name and message
     * @throws IOException when there're any conncetion problems
     */
    String[] receiveMsg() throws IOException;

    /**
     * send a msg to every connected client exept the client who orinal sent this message
     * @param originSocket the socket of the client who sent this message
     * @param originName the name of the client who sent this message
     * @param msg the message to send
     * @throws IOException when there're any conncetion problems'
     */
    void sendMsgToEveryClientExeptTheoriginalSender(Socket originSocket, String originName, String msg) throws IOException;
}
