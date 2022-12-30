package object;

import main.GamePanel;

public class ObjectSetter {

    GamePanel gamePanel;

    public ObjectSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

        //Instantiate desk and give it a default location.
        gamePanel.gameObject[0] = new ComputerDesk();
        gamePanel.gameObject[0].worldX = 30 * gamePanel.tileSize;
        gamePanel.gameObject[0].worldY = 7 * gamePanel.tileSize;

        //Instantiate desk and give it a default location.
        gamePanel.gameObject[1] = new Coffee();
        gamePanel.gameObject[1].worldX = 30 * gamePanel.tileSize;
        gamePanel.gameObject[1].worldY = 23 * gamePanel.tileSize;

        gamePanel.gameObject[2] = new Coffee();
        gamePanel.gameObject[2].worldX = 31 * gamePanel.tileSize;
        gamePanel.gameObject[2].worldY = 20 * gamePanel.tileSize;

        gamePanel.gameObject[3] = new Coffee();
        gamePanel.gameObject[3].worldX = 32 * gamePanel.tileSize;
        gamePanel.gameObject[3].worldY = 21 * gamePanel.tileSize;

        gamePanel.gameObject[3] = new Coffee();
        gamePanel.gameObject[3].worldX = 29 * gamePanel.tileSize;
        gamePanel.gameObject[3].worldY = 22 * gamePanel.tileSize;

        gamePanel.gameObject[4] = new Code();
        gamePanel.gameObject[4].worldX = 29 * gamePanel.tileSize;
        gamePanel.gameObject[4].worldY = 7 * gamePanel.tileSize;

        gamePanel.gameObject[5] = new Code();
        gamePanel.gameObject[5].worldX = 31 * gamePanel.tileSize;
        gamePanel.gameObject[5].worldY = 7 * gamePanel.tileSize;

        gamePanel.gameObject[6] = new SuperDesk();
        gamePanel.gameObject[6].worldX = 52 * gamePanel.tileSize;
        gamePanel.gameObject[6].worldY = 10 * gamePanel.tileSize;
    }
}
