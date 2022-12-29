package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    int hasCoffee = 0;
    int hasDesk = 0;

    //Player CTOR
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        setGamePanel(gamePanel);
        setKeyHandler(keyHandler);

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidAreaPlayer = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidAreaPlayer.x; //This value is taken from the instantiated rectangle.
        solidAreaDefaultY = solidAreaPlayer.y; //Same source of value.

        setDefaultValues();
        getPlayerImage();
    }

    //Default location placement, "direction facing", and player speed to walk.
    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 20;
        worldY = gamePanel.tileSize * 20;
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
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed
                || keyHandler.rightPressed) {


            //Each key that is pressed has a corresponding action and image that is created with the following if chain statement.
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }

            //Check for tile collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            //check object collision
            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            //if collision is false, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            //Player image changes every 10 seconds.
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
            spriteCounter++;
        }
    }

    public void pickUpObject(int index) {

        if (index != 999) {
            String objectName = gamePanel.gameObject[index].name;
            switch (objectName) {
                case "Coffee":
                    speed += 2;
                    gamePanel.playSoundEffect(6);
                    hasCoffee++;
                    gamePanel.gameObject[index] = null;
                    System.out.println("You picked up a key!");
                    break;
                case "Desk":
                    if(hasCoffee > 0) {
                        gamePanel.stopMusic();
                        gamePanel.playSoundEffect(4);
                        gamePanel.gameObject[index] = null;
                        hasDesk++;
                        hasCoffee--;
                        System.out.println("Used a coffee to pick up desk.");
                    }
                    break;
                case "Code":
                    gamePanel.playSoundEffect(7);
                    gamePanel.gameObject[index] = null;
                    break;


            }
            System.out.println("You have " + hasCoffee + " Coffee and " + hasDesk + " Desk.");
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
