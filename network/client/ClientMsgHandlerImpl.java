package network.client;

import java.io.DataInputStream;
import java.net.Socket;
import UI.*;

public class ClientMsgHandlerImpl implements ClientMsgHanderInterface{
    private Socket socket;
    DataInputStream dais = null;
    UserInterface ui;

    public ClientMsgHandlerImpl(Socket socket) {
        this.socket = socket;
        ui = new UserInterfaceImpl();
    }

    public void run() {

        try {
            dais = new DataInputStream(this.socket.getInputStream());
            
        } catch (Exception e) {
            System.out.println("error at creating client inout stram" + e.getMessage());
        }
        while (true) {
            try {
                ui.displayChatMsg(dais.readUTF(), dais.readUTF()); //name, msg
            } catch (Exception e) {
                System.out.println(e.getMessage());
                try {
                    this.socket.close();   
                } catch (Exception expt) {
                    // ignore
                }
                break;
            }
        }
    }

}
