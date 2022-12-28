package entity;

import java.awt.image.BufferedImage;

//Super class that sets up all the environmental variables for speeds, players, NPCs, etc.
public class Entity {


    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage playerUp1;
    public BufferedImage playerUp2;
    public BufferedImage playerDown1;
    public BufferedImage playerDown2;
    public BufferedImage playerLeft1;
    public BufferedImage playerLeft2;
    public BufferedImage playerRight1;
    public BufferedImage playerRight2;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

}
