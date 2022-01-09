import entity.Board;
import entity.Player;
import exception.OutOfMapException;
import exception.SpawnOnThreeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class PlayerTest {

    private Board board;
    private Player player;

    @Before
    public void initBoard() throws IOException {
        board = new Board(Path.of("src/test/resources/maps/carte.txt"));
        board.init();
        player = new Player();
    }

    @Test
    public void checkGoodSpanwn() throws SpawnOnThreeException, OutOfMapException {
        System.out.println("---- Start of checkGoodSpanwn test ----");
        player.setStartX(3);
        player.setStartY(0);
        Assert.assertTrue(player.checkSpawn(board.getGameMap()));
        System.out.println("---- End of checkGoodSpanwn test ----");
    }

    @Test(expected = OutOfMapException.class)
    public void checkOutOfMapException() throws SpawnOnThreeException, OutOfMapException {
        System.out.println("---- Start of checkOutOfMapException test ----");
        player.setStartX(-1);
        player.setStartY(0);
        player.checkSpawn(board.getGameMap());
        System.out.println("---- End of checkOutOfMapException test ----");
    }

    @Test(expected = SpawnOnThreeException.class)
    public void checkSpawnOnThreeException() throws SpawnOnThreeException, OutOfMapException {
        System.out.println("---- Start of checkSpawnOnThreeException test ----");
        player.setStartX(1);
        player.setStartY(1);
        player.checkSpawn(board.getGameMap());
        System.out.println("---- End of checkSpawnOnThreeException test ----");
    }

    @Test
    public void movingTest() {
        System.out.println("---- Start of movingTest  ----");
        player.setStartX(3);
        player.setStartY(0);
        player.setTravel(Arrays.asList("SSSSEEEEEENN".split("")));
        player.moving(board.getGameMap());
        Assert.assertEquals(player.getX(),9);
        Assert.assertEquals(player.getY(),2);
        System.out.println("---- End of movingTest  ----");
    }

    @Test
    public void initTest() throws IOException {
        System.out.println("---- Start of initTest  ----");
        player = new Player(Path.of("src/main/resources/maps/travel.txt"));
        player.init();
        Assert.assertEquals(player.getStartX(),3);
        Assert.assertEquals(player.getStartY(),0);
        System.out.println("---- End of initTest  ----");
    }
}
