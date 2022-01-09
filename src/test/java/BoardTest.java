import entity.Board;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class BoardTest {

    private Board board;

    @Test
    public void initTest() throws IOException, URISyntaxException {
        System.out.println("Test of map initialization");
        Board board = new Board(Path.of("src/test/resources/maps/carte.txt"));
        board.init();
        Assert.assertNotNull(board.getGameMap());
        Assert.assertEquals(board.getWidth(),20);
        Assert.assertEquals(board.getHeight(),20);
    }
}
