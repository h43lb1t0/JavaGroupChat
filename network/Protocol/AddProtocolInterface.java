package network.Protocol;

public interface AddProtocolInterface extends ProtocolInterface{

    /**
     * splits themsq into the command parts
     * @param msgthe message/command
     * @return an String[] of the command parts
     */
    String[] splitMsg(String msg);

    /**
     * convert the command parts of the String[] to an int[]
     * @param commandParts String[]
     * @return int[]
     */
    int[] convertStringToIntArray(String[] commandParts);

    /**
     * add the two numbers of the int[]
     * @param numbers int[]
     * @return the result
     */
    int addtwoNumbers(int[] numbers);
}
