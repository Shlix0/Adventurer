package exception;

public class SpawnOnThreeException extends Exception{
    public SpawnOnThreeException(String coordinate) {
        super(coordinate + "spawn is on a three");
    }
}
