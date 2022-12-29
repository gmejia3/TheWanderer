package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

//Parent class of all objects
public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;
    public Rectangle solidAreaObject = new Rectangle(0, 0, 48, 48);
    public int objectAreaDefaultX = 0;
    public int objectAreaDefaultY = 0;


    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {

        int playerScreenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int playerScreenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        //Render only the area that is in view of the camera.
        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

            graphics2D.drawImage(image, playerScreenX, playerScreenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }

    }

}
