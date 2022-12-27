package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        //Array is for every type of tile.
        tile = new Tile[10];

        getTitleImage();
    }

    public void getTitleImage() {
        try {

            tile[0] = new Tile();

            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/ground.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/backgroundTiles/wall.png"));




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {

        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {
            graphics2D.drawImage(tile[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
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
