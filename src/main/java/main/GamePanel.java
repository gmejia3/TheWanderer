package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.ObjectSetter;
import object.SuperObject;
import tile.CollisionChecker;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTileSize = 16; // 16x16 tile standard size for retro 2d games.
    final int scale = 3; //Scare the image to make it bigger for high resolution screens.
    public final int tileSize = originalTileSize * scale; //48x48 tile size
    public final int maxScreenColumn = 16; //Width of the screen
    public final int maxScreenRow = 12; //Height of the screen
    public final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //World Settings
    public final int maxWorldColumn = 62;
    public final int maxWorldRow = 34;
    int FPS = 60;

    //System
    public TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Sound sound = new Sound();

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public ObjectSetter objectSetter = new ObjectSetter(this);
    Thread gameThread;



    //Entity and Object
    public Player player = new Player(this, keyHandler);
    public SuperObject[] gameObject = new SuperObject[10];


    //GamePanel Ctor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.orange);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        objectSetter.setObject();
        //select a song by choosing an index value from the Sound class.
        playMusic(3);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000 /FPS; //0.0166 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                //1. Update information such as character positions.
                updateScreen();

                //2. Draw the screen with the updated information.
                repaint();

                //Update time
                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void updateScreen() {
    player.update();
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        //Tile
        tileManager.draw(graphics2D);

        //Game Objects
        for (int i = 0; i < gameObject.length; i++) {
            if(gameObject[i] != null) {
                gameObject[i].draw(graphics2D, this);
            }
        }

        //Player
        player.draw(graphics2D);

        graphics2D.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }


}
