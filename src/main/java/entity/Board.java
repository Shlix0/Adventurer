package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Getter
@Setter
public class Board {

    private int height;
    private int width;
    private Path pathMap;
    private String[][] gameMap;

    public void init() throws IOException {
        try {
            Stream<String> line = Files.lines(pathMap);
            List<String> list = line.collect(Collectors.toList());
            this.height = list.size();
            this.width = list.get(0).split("").length;
            this.gameMap = new String[width][height];
            for (int i = 0; i < width; i++) {
                gameMap[i] = list.get(i).split("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Board(Path pathMap) throws IOException {
        this.pathMap = pathMap;
    }
}
