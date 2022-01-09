package exception;

public class OutOfMapException extends Exception{
    public OutOfMapException(String coordinate) {
        super(coordinate + " is out of map");
    }
}
