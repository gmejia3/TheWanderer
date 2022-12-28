package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        //Array is for every type of tile.
        tile = new Tile[10];
        mapTileNumber = new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];

        getTitleImage();
        loadMap("/maps/FloorMap1.txt");
    }

    public void getTitleImage() {
        try {

            tile[0] = new Tile();

            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/ground.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/CoolDesignTile.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/AnotherColor.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/AnotherColor1.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String fileName) {

        try {

            InputStream inputFile = getClass().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader( new InputStreamReader(inputFile));

            int column = 0;
            int row = 0;

            while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {

                String line = reader.readLine();

                while (column < gamePanel.maxScreenColumn) {
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);
                    mapTileNumber[column][row] = number;
                    column++;
                }
                if (column == gamePanel.maxScreenColumn) {
                    column = 0;
                    row++;
                }

            }
            reader.close();

        } catch (Exception e) {

        }

    }


    public void draw(Graphics2D graphics2D) {

        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {

            int tileNumber = mapTileNumber[column][row];

            graphics2D.drawImage(tile[tileNumber].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            column++;
            x += gamePanel.tileSize;

            if(column == gamePanel.maxScreenColumn) {
                column = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }

}
