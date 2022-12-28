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

    //TileManager Ctor
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        //Array is for every type of tile.
        tile = new Tile[10];
        mapTileNumber = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

        getTitleImage();
        loadMap("/maps/WorldMap.txt");
    }

    //Obtain all the tiles from resources.
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

            while (column < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow) {

                String line = reader.readLine();

                while (column < gamePanel.maxWorldColumn) {
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);
                    mapTileNumber[column][row] = number;
                    column++;
                }
                if (column == gamePanel.maxWorldColumn) {
                    column = 0;
                    row++;
                }

            }
            reader.close();

        } catch (Exception e) {

        }

    }


    public void draw(Graphics2D graphics2D) {

        int worldColumn = 0;
        int worldRow = 0;


        while (worldColumn < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow) {

            int tileNumber = mapTileNumber[worldColumn][worldRow];

            int worldX = worldColumn * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int playerScreenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int playerScreenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            //Render only the area that is in view of the camera.
            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                graphics2D.drawImage(tile[tileNumber].image, playerScreenX, playerScreenY, gamePanel.tileSize, gamePanel.tileSize, null);

            }

            worldColumn++;


            if(worldColumn == gamePanel.maxWorldColumn) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }

}
