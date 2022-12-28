package tile;

import entity.Entity;
import main.GamePanel;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.playerBody.x;
        int entityRightWorldX = entity.worldX + entity.playerBody.x + entity.playerBody.width;
        int entityTopWorldY = entity.worldY + entity.playerBody.y;
        int entityBottomWorldY = entity.worldY + entity.playerBody.y + entity.playerBody.height;

        int entityLeftColumn = entityLeftWorldX/gamePanel.tileSize;
        int entityRightColumn = entityRightWorldX/ gamePanel.tileSize;
        int entityTopRow = entityTopWorldY/ gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY/ gamePanel.tileSize;

        int tileNumber1;
        int tileNumber2;

        switch (entity.direction) {

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityTopRow];

                if(gamePanel.tileManager.tile[tileNumber1].collision || gamePanel.tileManager.tile[tileNumber2].collision) {
                    entity.collisionOn = true;
                }

                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityBottomRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNumber1].collision || gamePanel.tileManager.tile[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[entityLeftColumn][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNumber1].collision || gamePanel.tileManager.tile[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityTopRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightColumn][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNumber1].collision || gamePanel.tileManager.tile[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }


    }
}
