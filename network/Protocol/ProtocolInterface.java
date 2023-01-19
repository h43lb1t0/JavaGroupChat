package network.Protocol;

public interface ProtocolInterface {

    /**
     * detects if a msg is a command
     * @param input the message/command
     * @param commandType
     * @return true if the msg is a command
     */
    boolean detectProtocol(String input, String commandType);

    /**
     * return if the msg is a protocol
     * @return true if the msg is a protocol
     */
    boolean getIfIsProtocol();

}