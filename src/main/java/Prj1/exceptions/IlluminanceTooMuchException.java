package Prj1.exceptions;
/**
* The exception shows that parameter of illuninance is too big.*/
public class IlluminanceTooMuchException extends Exception implements BuildingException {
    /**
     * Constructor with input message. It calls a suitable super constructor with the message.*/
    public IlluminanceTooMuchException(String message) {
        super(message);
    }
}
