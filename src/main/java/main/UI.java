package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.Coffee;

public class UI {

    GamePanel gamePanel;
    //Setting up the font. Font, Font style, Font size
    Font fontArial;
    Font finishFontArial;
    BufferedImage coffeeImage;
    public  boolean userMessageOn = false;
    public String userMessage = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTimeTracker;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    //UI Ctor
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        fontArial = new Font("Arial", Font.PLAIN, 40);
        finishFontArial = new Font("Arial", Font.BOLD,80);
        Coffee coffee = new Coffee();
        coffeeImage = coffee.image;
    }

    //Message is activated when an item is picked up and displayed to the user once the method is called.
    public void showMessage(String text) {
        userMessage = text;
        userMessageOn = true;
    }
    public void draw(Graphics2D graphics2D) {

        if (gameFinished) {
            gamePanel.stopSoundEffect();
            //gamePanel.playSoundEffect(0);
            graphics2D.setFont(fontArial);
            graphics2D.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            //Create the super desk message
            text = "You captured the Super Desk!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();

            //Place the message at the center.
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 - (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x ,y);


            //Create the final time message
            graphics2D.setColor(Color.black);
            text = "Your time is :" + decimalFormat.format(playTimeTracker) + "!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 4);
            graphics2D.drawString(text, x ,y);

            //Create the congrats Screen!
            graphics2D.setFont(finishFontArial);
            graphics2D.setColor(Color.RED);

            text = "Congratulations!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();

            //Place the message below the message above at the center.
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x ,y);

            //Stop the game thread.
            gamePanel.gameThread = null;
        }

        else {

            graphics2D.setFont(fontArial);
            graphics2D.setColor(Color.white);
            graphics2D.drawImage(coffeeImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2,
                    gamePanel.tileSize, gamePanel.tileSize, null);
            //Display settings for the character on the top left of the screen.
            graphics2D.drawString("x " + gamePanel.player.hasCoffee, 80, 57);

            //Time Display
            //Time iterates but only resets once per second in 60 FPS.
            playTimeTracker +=(double) 1/60;
            graphics2D.drawString("Time: "+ decimalFormat.format(playTimeTracker), gamePanel.tileSize*11, 57);

            //Message details
            if (userMessageOn) {
                //change the font size for the message.
                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                //Settings for the display location on screen.
                graphics2D.drawString(userMessage, gamePanel.tileSize / 2, gamePanel.tileSize * 5);

                messageCounter++;
                //Message will disappear after 2 seconds.
                if (messageCounter > 60) {
                    messageCounter = 0;
                    userMessageOn = false;
                }
            }
        }
    }
}
