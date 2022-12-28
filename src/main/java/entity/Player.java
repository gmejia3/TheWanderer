package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

//Player class to set up the player object.
public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    //Player CTOR
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        setGamePanel(gamePanel);
        setKeyHandler(keyHandler);

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    //Default location placement, "direction facing", and player speed to walk.
    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    //Get the player images from the resources.
    public void getPlayerImage() {
        try {

            playerUp1 = ImageIO.read(getClass().getResourceAsStream("/playerImages/boy_up_1.png"));
            playerUp2 = ImageIO.read(getClass().getResourceAsStream("/playerImages/boy_up_2.png"));
            playerDown1 = ImageIO.read(getClass().getResourceAsStream("/playerImages/boy_down_1.png"));
            playerDown2 = ImageIO.read(getClass().getResourceAsStream("/playerImages/boy_down_2.png"));
            playerLeft1 = ImageIO.read(getClass().getResourceAsStream("/playerImages/boy_left_1.png"));
            playerLeft2 = ImageIO.read(getClass().getResourceAsStream("/playerImages/boy_left_2.png"));
            playerRight1 = ImageIO.read(getClass().getResourceAsStream("/playerImages/boy_right_1.png"));
            playerRight2 = ImageIO.read(getClass().getResourceAsStream("/playerImages/boy_right_2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Method gets called 60 times per second.
    public void update() {

        //The sprite will not switch between pictures to show a walking motion unless a key is pressed.
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            spriteCounter++;
        }

        //Each key that is pressed has a corresponding action and image that is created with the following if chain statement.
        if (keyHandler.upPressed) {
            direction = "up";
            worldY -= speed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            worldY += speed;
        } else if (keyHandler.leftPressed) {
            direction = "left";
            worldX -= speed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            worldX += speed;
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

    //Sets up the image for the sprite to walk depending on direction.
    public void draw(Graphics2D graphics2D) {

        //Set up with a local variable
        BufferedImage image = null;

        //Switch statement to transition between both images based on "direction" the user is walking.
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

        //Takes in the image from the switch statement and draws the image on the screen.
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);


    }


    //Getters and Setters
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
