package tile;

import entity.Entity;
import main.GamePanel;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidAreaPlayer.x;
        int entityRightWorldX = entity.worldX + entity.solidAreaPlayer.x + entity.solidAreaPlayer.width;
        int entityTopWorldY = entity.worldY + entity.solidAreaPlayer.y;
        int entityBottomWorldY = entity.worldY + entity.solidAreaPlayer.y + entity.solidAreaPlayer.height;

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

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for(int i = 0; i < gamePanel.gameObject.length; i++) {
            if(gamePanel.gameObject[i] != null) {
                //Get entity's solid area position
                entity.solidAreaPlayer.x = entity.worldX + entity.solidAreaPlayer.x;
                entity.solidAreaPlayer.y = entity.worldY + entity.solidAreaPlayer.y;

                //Get the objects solid area position
                gamePanel.gameObject[i].solidAreaObject.x = gamePanel.gameObject[i].worldX + gamePanel.gameObject[i].solidAreaObject.x;
                gamePanel.gameObject[i].solidAreaObject.y = gamePanel.gameObject[i].worldY + gamePanel.gameObject[i].solidAreaObject.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidAreaPlayer.y -= entity.speed;
                        if (entity.solidAreaPlayer.intersects(gamePanel.gameObject[i].solidAreaObject)) {
                            if(gamePanel.gameObject[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidAreaPlayer.y += entity.speed;
                        if (entity.solidAreaPlayer.intersects(gamePanel.gameObject[i].solidAreaObject)) {
                            if(gamePanel.gameObject[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidAreaPlayer.x -= entity.speed;
                        if (entity.solidAreaPlayer.intersects(gamePanel.gameObject[i].solidAreaObject)) {
                            if(gamePanel.gameObject[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidAreaPlayer.x += entity.speed;
                        if (entity.solidAreaPlayer.intersects(gamePanel.gameObject[i].solidAreaObject)) {
                            if(gamePanel.gameObject[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidAreaPlayer.x = entity.solidAreaDefaultX;
                entity.solidAreaPlayer.y = entity.solidAreaDefaultY;
                gamePanel.gameObject[i].solidAreaObject.x = gamePanel.gameObject[i].objectAreaDefaultX;
                gamePanel.gameObject[i].solidAreaObject.y = gamePanel.gameObject[i].objectAreaDefaultY;
            }
        }


        return index;
    }


}
