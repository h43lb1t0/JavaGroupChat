package network.Protocol;
import UI.*;

public class AddProtocolImpl implements AddProtocolInterface {

    private int result;
    private boolean isProtocol;

    public AddProtocolImpl(String input) {
        
        String commandType = "\\+\\s[0-9]+\\s[0-9]+";

        this.isProtocol = this.detectProtocol(input, commandType);

        if (this.isProtocol) {
            UserInterface ui = new UserInterfaceImpl();
            result = addtwoNumbers(convertStringToIntArray(splitMsg(input)));

            ui.DisplayProtocolResult("+ = " + result); 

            //"+ = " + [die beiden Zahlen] + " " + [Ergebnis].

        }
    }

    public boolean detectProtocol(String input, String commandType) {
        if (input.matches(commandType)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String[] splitMsg(String msg) {
        return msg.split(" ");
    }
    
    public int[] convertStringToIntArray(String[] commandParts) {
        int numA = Integer.parseInt(commandParts[1]);
        int numB = Integer.parseInt(commandParts[2]);
        return new int[] {numA, numB};
    }

    public int addtwoNumbers(int[] numbers) {
        return numbers[0] + numbers[1];
    }

    public boolean getIfIsProtocol() {
        return this.isProtocol;
    }
}
