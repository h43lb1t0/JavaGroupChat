package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import network.Protocol.*;

public class UserInterfaceImpl implements UserInterface {

    public void welcomeMsg() {
        System.out.println("welcome at this great chat app!\n");
    }

    public String askForUserName() {
        System.out.println("What do you want to be called: ");
        String name = userInputReader(false);
        System.out.println("You're: " + name);
        return name;
    }

    public boolean askIfWantsToBeServer() {
        boolean server = false;
        System.out.println("do you want to be the server? (yes/no)");
        switch (userInputReader(false).toLowerCase()) {
            case "yes", "y" -> server = true;
            case "no", "n" -> server = false;
            default -> server = false;
        }
        return server;
    }

    public void displayChatMsg(String senderName, String msg) throws IllegalArgumentException{
        if (senderName == null || msg == null) {
            throw new IllegalArgumentException("null values are not allowed in chat");
        }

        System.out.println("[" + senderName + "]: " + msg);
    }

    public String readChatMsgToBeSent() {
        return userInputReader(true);
    }

    public String userInputReader(boolean checkForProtocol) {
        String msg;
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            msg = reader.readLine();
        } catch (Exception e) {
            msg = "Error while trying to get other users message";
        }

        if (checkForProtocol) {
            AddProtocolImpl adder = new AddProtocolImpl(msg);
            if (!adder.getIfIsProtocol()) {
                return msg;
            }
        }
        return "sent a protocol";
    }

    public void DisplayProtocolResult(String ProtocolResult) {
        System.out.println(ProtocolResult);
    }
}
