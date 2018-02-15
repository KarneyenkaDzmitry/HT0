package Prj1.exceptions;
/**
 * The class needs to make an exception when name isn't unique.*/
public class UniqueNameException extends Exception implements BuildingException {
    /**
     * Constructor with input message. It calls a suitable super constructor with the message.*/
    public UniqueNameException(String message) {
        super(message);
    }
}
