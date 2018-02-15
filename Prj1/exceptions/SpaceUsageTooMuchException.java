package Prj1.exceptions;

public class SpaceUsageTooMuchException  extends Exception implements BuildingException {
    /**
     * Constructor with input message. It calls a suitable super constructor with the message.*/
    public SpaceUsageTooMuchException(String message) {
        super(message);
    }
}
