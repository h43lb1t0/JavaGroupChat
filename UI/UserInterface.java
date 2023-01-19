package UI;

import java.io.IOException;

public interface UserInterface {
    /**
     * shows a welcome msg to the user
     */
    void welcomeMsg();

    /**
     * Get the name the user want his client to be called
     * @return the name of the user
     */
    String askForUserName();

    /**
     * Aks the user if he wants to be the Server
     * @return wants to be the Server
     */
    boolean askIfWantsToBeServer();

    /**
     * displays a recived massage
     * @param senderName name of the msq sender
     * @param message msg conntent
     * @throws IllegalArgumentException
     */
    void displayChatMsg(String senderName, String message) throws IllegalArgumentException;

    /**
     * Get the msg the user entered
     * @return the msg the user wants to send
     */
    String readChatMsgToBeSent();

    /**
     * Get any input of the user
     * @return the users Input
     * @throws IOException
     */
    String userInputReader();

}
