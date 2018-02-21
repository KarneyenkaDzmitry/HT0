package Prj1.exceptions;

public class IncorrectNameOfRoomException extends Exception implements BuildingException {
    public IncorrectNameOfRoomException(String message) {
        super(message);
    }
}
