package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        setGamePanel(gamePanel);
        setKeyHandler(keyHandler);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 200;
        y = 500;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            playerUp1 = ImageIO.read(getClass().getResourceAsStream("/boy_up_1.png"));
            playerUp2 = ImageIO.read(getClass().getResourceAsStream("/boy_up_2.png"));
            playerDown1 = ImageIO.read(getClass().getResourceAsStream("/boy_down_1.png"));
            playerDown2 = ImageIO.read(getClass().getResourceAsStream("/boy_down_2.png"));
            playerLeft1 = ImageIO.read(getClass().getResourceAsStream("/boy_left_1.png"));
            playerLeft2 = ImageIO.read(getClass().getResourceAsStream("/boy_left_2.png"));
            playerRight1 = ImageIO.read(getClass().getResourceAsStream("/boy_right_1.png"));
            playerRight2 = ImageIO.read(getClass().getResourceAsStream("/boy_right_2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Method gets called 60 times per second.
    public void update() {

        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            spriteCounter++;
        }

        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
        } else if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }



        //Player image changes every 10 seconds.
        if(spriteCounter > 12) {
            if(spriteNumber == 1) {
                spriteNumber = 2;
            } else if ( spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D graphics2D) {

//        graphics2D.setColor(Color.blue);
//        graphics2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = playerUp1;
                }
                if (spriteNumber == 2) {
                    image = playerUp2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = playerDown1;
                }
                if (spriteNumber == 2) {
                    image = playerDown2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = playerLeft1;
                }
                if (spriteNumber == 2) {
                    image = playerLeft2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = playerRight1;
                }
                if (spriteNumber == 2) {
                    image = playerRight2;
                }
                break;
        }

        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);


    }


    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
}
