import entity.Player;
import entity.Board;
import exception.OutOfMapException;
import exception.SpawnOnThreeException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class aventureApp {

    public static void main(String[] args) throws IOException, SpawnOnThreeException, OutOfMapException {
        Path boardPath;
        Path playerPath;

        // Prepare and check Path //
        try {
            boardPath = Path.of("src/main/resources/maps/carte.txt");
            playerPath = Path.of("src/main/resources/maps/travel.txt");
        } catch (NullPointerException e) {
            throw new NullPointerException("File not found");
        }

        // Initialise Board and Player //
        Board board = new Board(Path.of("src/main/resources/maps/carte.txt"));
        board.init();
        Player player = new Player(Path.of("src/main/resources/maps/travel.txt"));
        player.init();

        // Run the travel on the board gameMap //
        player.run(board.getGameMap());

    }

}
