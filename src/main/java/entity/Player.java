package entity;

import exception.OutOfMapException;
import exception.SpawnOnThreeException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@NoArgsConstructor
@Getter
@Setter
public class Player {

    private int startX;
    private int startY;
    private List<String> travel;
    private Path path;
    private int x;
    private int y;

    /**
     * Init the player with travel and spawn point
     * @throws IOException
     */
    public void init() throws IOException {
        if (this.path != null) {
            Stream<String> line = Files.lines(path);
            List<String> list = line.collect(Collectors.toList());
            this.travel = Arrays.asList(list.get(0).split(""));
            this.startX = Character.getNumericValue(list.get(1).charAt(0));
            this.startY = Character.getNumericValue(list.get(1).charAt(1));
        } else {
            System.out.println("Player's path is null, please try to inform it");
        }
    }

    /**
     * Run the travel.txt of character on the game map.
     * The game map is 2D representation with String[][]
     * @param gameMap
     */
    public void run(String[][] gameMap) throws SpawnOnThreeException, OutOfMapException {
        System.out.println("Adventurer starting to walk from " +  this.getStartX() + " " + this.getStartY());
        if (checkSpawn(gameMap)) {
            moving(gameMap);
        }
    }

    /**
     * Char moving from a list of cardinal point.
     * N = North / S = South / O = West / E = East
     * @param gameMap
     */
    public void moving (String[][] gameMap)  {

        this.x = startX;
        this.y = startY;

        this.travel.forEach(direction -> {
            switch (direction) {
                case "N" :
                    if (this.y - 1 > 0 && gameMap[this.y - 1][this.x].equals(" ")) {
                        this.setY(this.y -1);
                    }
                    break;
                case "S" :
                    if (this.y + 1 <= gameMap.length && gameMap[this.y + 1][this.x].equals(" ")) {
                        this.setY(this.y + 1);
                    }
                    break;
                case "O" :
                    if (this.x - 1 > 0 && gameMap[this.y][this.x - 1].equals(" ")) {
                        this.setX(this.x - 1);
                    }
                    break;
                case "E" :
                    if (this.x + 1<= gameMap[0].length && gameMap[this.y][this.x + 1].equals(" ")){
                        this.setX(this.x + 1);
                    }
                    break;
                default:
                    System.out.println("This entry is not a cardinal point.");
            }
        });
        System.out.println("Position : " + this.x + " " + this.y );
    }

    /**
     * Check if the spawn is good (not three and not out of the map)
     * @param gameMap
     * @throws OutOfMapException
     * @throws SpawnOnThreeException
     */
    public boolean checkSpawn(String[][] gameMap) throws OutOfMapException, SpawnOnThreeException {
        boolean available = true;
        if (this.getStartY() > gameMap.length || this.getStartX() > gameMap[0].length || this.getStartY() < 0 || this.getStartX() < 0) {
            throw new OutOfMapException("x : " + this.getStartX() +" , y :" + this.getStartY());
        }
        if (gameMap[this.getStartY()][this.getStartX()].equals("#")) {
            throw new SpawnOnThreeException("x : " + this.getStartX() +" , y :" + this.getStartY());
        }
        return available;
    }

    public Player(int startX, int startY, List<String> travel) {
        this.startX = startX;
        this.startY = startY;
        this.travel = travel;
    }

    public Player(Path path) {
        this.path = path;
    }
}
